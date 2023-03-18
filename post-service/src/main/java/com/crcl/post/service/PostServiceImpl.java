package com.crcl.post.service;

import com.crcl.common.exceptions.EntityNotFoundException;
import com.crcl.post.annotations.ValidCreatePostRequest;
import com.crcl.post.client.IdpClient;
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
import com.crcl.post.utils.CrclFileUtils;
import com.crcl.post.utils.CrclUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final StorageClient storageClient;
    private final IdpClient idpClient;
    private final TagRepository tagRepository;

    @Override
    public PostDto save(PostDto postDto) {
        Post save = postRepository.save(postMapper.toEntity(postDto));
        return postMapper.toDto(save);
    }

    @Override
    @Transactional
    public PostDto save(@ValidCreatePostRequest CreatePostRequest request) {
        Post post = new Post();
        CrclUtils.applyIf(CrclFileUtils.hasFiles(request.getFiles()), () -> this.addFiles(request, post));
        CrclUtils.applyIfNotEmpty(request.getSharedWithUsers(), () -> this.addSharedWithUsers(request, post));
        CrclUtils.applyIfNotEmpty(request.getTags(), () -> this.addPostTags(request, post));
        CrclUtils.applyIfNotEmpty(request.getTaggedUsers(), () -> this.addTaggedUsers(request, post));
        return postMapper.toDto(postRepository.save(post));
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
                        () -> new EntityNotFoundException(""));
    }

    @Override
    public PostDto findById(String s) {
        return postRepository.findById(s)
                .map(postMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(""));
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
                .orElseThrow(() -> new EntityNotFoundException(""));
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
                        .setId(index.getAndIncrement())
                        .setContentType(file.getContentType())
                        .setUrl(file.getLink()))
                .forEach(image -> post.getImages().add(image));

        index.set(0);

        results.stream()
                .filter(fileUploadResult -> fileUploadResult.getContentType().toLowerCase().startsWith("video"))
                .map(file -> new Video()
                        .setId(index.incrementAndGet())
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
        List<String> names = request.getTags().stream()
                .filter(Tag::isSystem)
                .map(Tag::getName)
                .toList();
        List<Tag> tags = tagRepository.findByNameIn(names);
        post.setTags(new LinkedHashSet<>(tags));
    }

}
