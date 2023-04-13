package com.crcl.post.service.impl;

import com.crcl.common.dto.queue.DefaultQEvent;
import com.crcl.common.exceptions.EntityNotFoundException;
import com.crcl.common.queue.ImageUpload;
import com.crcl.post.annotations.ValidCreatePostRequest;
import com.crcl.post.client.IdpClient;
import com.crcl.post.client.ProfileClient;
import com.crcl.post.client.StorageClient;
import com.crcl.post.domain.Image;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Tag;
import com.crcl.post.domain.Video;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.dto.PostDto;
import com.crcl.post.mapper.PostMapper;
import com.crcl.post.repository.PostRepository;
import com.crcl.post.repository.TagRepository;
import com.crcl.post.service.PostQueueService;
import com.crcl.post.service.PostService;
import com.crcl.post.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.crcl.post.utils.CrclFileUtils.hasFiles;
import static com.crcl.post.utils.CrclUtils.applyIf;
import static com.crcl.post.utils.CrclUtils.applyIfNotEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final StorageClient storageClient;
    private final IdpClient idpClient;
    private final TagRepository tagRepository;
    private final PostQueueService queueService;
    private final UserService userService;
    private final ProfileClient profileClient;

    @Override
    public PostDto save(PostDto postDto) {
        Post save = postRepository.save(postMapper.toEntity(postDto));
        return postMapper.toDto(save);
    }

    @Override
    @Transactional
    public PostDto save(@ValidCreatePostRequest CreatePostRequest request) {
        Post post = postMapper.toEntity(request);
        applyIf(hasFiles(request.getFiles()), () -> addFiles(request, post));
        applyIfNotEmpty(request.getSharedWithUsers(), () -> addSharedWithUsers(request, post));
        applyIfNotEmpty(request.getTags(), () -> addPostTags(request, post));
        applyIfNotEmpty(request.getTaggedUsers(), () -> addTaggedUsers(request, post));
        post.setCreator(profileClient.findByUsername(userService.getCurrentUser().getUsername()));
        PostDto savedPost = postMapper.toDto(postRepository.save(post));
        queueService.publishCreatePostEvent(savedPost);

        return savedPost;
    }

    @Override
    public void synchronize(DefaultQEvent<ImageUpload> event) {
        var imageId = event.getPayload().getId();
        var fileUploadResult = event.getPayload().getResponse();
        var imageSize = event.getPayload().getImageSize();

        Optional<Post> postOptional = postRepository.findByImageId(imageId);
        postOptional.ifPresent(post -> post.getImages().stream()
                .filter(image -> image.getId().equals(imageId))
                .findFirst()
                .ifPresentOrElse(image -> {
                            var imageToStore = new Image()
                                    .setImageSize(imageSize)
                                    .setParent(fileUploadResult.getEtag())
                                    .setContentType(fileUploadResult.getContentType())
                                    .setUrl(fileUploadResult.getLink());
                            image.getProcessedImages().add(imageToStore);
                            postRepository.save(postOptional.get());
                        },
                        () -> log.info("no image found for id " + imageId)
                ));
    }

    @Override
    public List<PostDto> saveAll(List<PostDto> entitiesDto) {
        return entitiesDto.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public void deleteById(String entityId) {
        postRepository.findById(entityId)
                .ifPresentOrElse(
                        post -> postRepository.deleteById(entityId),
                        EntityNotFoundException::new);
    }

    @Override
    public PostDto findById(String s) {
        return postRepository.findById(s)
                .map(postMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<PostDto> findAll() {
        return this.postRepository.findAll()
                .stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    public Page<PostDto> findAll(Pageable pageable) {
        return postRepository.findAll(pageable).map(postMapper::toDto);

    }

    @Override
    public PostDto update(PostDto postDto, String entityId) {
        return postRepository.findById(entityId)
                .map(post -> postMapper.toEntity(postDto))
                .map(postRepository::save)
                .map(postMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    private void addSharedWithUsers(CreatePostRequest request, Post post) {
        var users = idpClient.findByUsername(new LinkedHashSet<>(request.getSharedWithUsers()));
        post.setSharedWithUsers(new LinkedHashSet<>(users));
    }

    private void addFiles(CreatePostRequest request, Post post) {
        final var index = new AtomicInteger(0);
        final var results = storageClient.saveAll(request.getFiles());

        results.stream()
                .filter(fileUploadResult -> fileUploadResult.getContentType().toLowerCase().startsWith("image"))
                .map(file -> new Image()
                        .setId(file.getEtag())
                        .setIndex(index.getAndIncrement())
                        .setContentType(file.getContentType())
                        .setUrl(file.getLink()))
                .forEach(image -> post.getImages().add(image));

        index.set(0);

        results.stream()
                .filter(fileUploadResult -> fileUploadResult.getContentType().toLowerCase().startsWith("video"))
                .map(file -> new Video()
                        .setId(file.getEtag())
                        .setIndex(index.incrementAndGet())
                        .setUrl(file.getLink()))
                .forEach(video -> post.getVideos().add(video));
    }

    private void addTaggedUsers(CreatePostRequest request, Post post) {
        var taggedUsers = this.idpClient.findByUsername(new LinkedHashSet<>(request.getTaggedUsers()));
        var usersTags = taggedUsers.stream()
                .map(userDto -> new Tag().setName(userDto.getUsername()))
                .collect(Collectors.toSet());
        post.getTags().addAll(usersTags);
    }

    private void addPostTags(CreatePostRequest request, Post post) {
        var names = request.getTags().stream()
                .filter(Tag::isSystem)
                .map(Tag::getName)
                .toList();
        var tags = tagRepository.findByNameIn(names);
        post.setTags(new LinkedHashSet<>(tags));
    }

}
