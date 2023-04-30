package com.crcl.post.service.impl;

import com.crcl.common.dto.UserDto;
import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.exceptions.EntityNotFoundException;
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
import com.crcl.post.dto.ProfileDto;
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

import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
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
    public PostDto save(@ValidCreatePostRequest CreatePostRequest request) {
        Post post = postMapper.toEntity(request);

        applyIf(hasFiles(request.getFiles()), () -> addFiles(request, post));
        applyIfNotEmpty(request.getSharedWithUsers(), () -> addSharedWithUsers(request, post));
        applyIfNotEmpty(request.getTags(), () -> addPostTags(request, post));
        applyIfNotEmpty(request.getTaggedUsers(), () -> addTaggedUsers(request, post));

        ProfileDto currentUserProfile = profileClient.findByUsername(userService.getCurrentUser().getUsername());
        post.setCreator(currentUserProfile);

        PostDto storedPost = postMapper.toDto(postRepository.save(post));

        queueService.publishCreatePostEvent(storedPost);

        return storedPost;
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
        List<UserDto> users = idpClient.findByUsername(new LinkedHashSet<>(request.getSharedWithUsers()));
        post.setSharedWithUsers(new LinkedHashSet<>(users));
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

    private void addFiles(CreatePostRequest request, Post post) {
        AtomicInteger index = new AtomicInteger(0);
        List<FileUploadResult> results = storageClient.saveAll(request.getFiles());

        results.stream()
                .filter(fileUploadResult -> fileUploadResult.getContentType().toLowerCase().startsWith("image"))
                .map(buildImage(index))
                .forEach(image -> post.getImages().add(image));

        index.set(0);

        results.stream()
                .filter(fileUploadResult -> fileUploadResult.getContentType().toLowerCase().startsWith("video"))
                .map(buildVideo(index))
                .forEach(video -> post.getVideos().add(video));
    }

    private Function<FileUploadResult, Image> buildImage(AtomicInteger index) {
        return file -> new Image()
                .setId(file.getEtag())
                .setIndex(index.getAndIncrement())
                .setContentType(file.getContentType())
                .setUrl(file.getLink());
    }

    private Function<FileUploadResult, Video> buildVideo(AtomicInteger index) {
        return file -> new Video()
                .setId(file.getEtag())
                .setIndex(index.incrementAndGet())
                .setUrl(file.getLink());
    }
}
