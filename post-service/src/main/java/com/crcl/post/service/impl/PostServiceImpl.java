package com.crcl.post.service.impl;

import com.crcl.common.dto.ProfileDto;
import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.exceptions.EntityNotFoundException;
import com.crcl.post.annotations.ValidCreatePostRequest;
import com.crcl.post.client.CommentClient;
import com.crcl.post.client.IdpClient;
import com.crcl.post.client.ProfileClient;
import com.crcl.post.client.StorageClient;
import com.crcl.post.domain.*;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.dto.PostDto;
import com.crcl.post.mapper.FileMapperRegistry;
import com.crcl.post.mapper.PostMapper;
import com.crcl.post.repository.PostRepository;
import com.crcl.post.repository.TagRepository;
import com.crcl.post.service.PostQueueService;
import com.crcl.post.service.PostService;
import com.crcl.post.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.crcl.post.utils.CrclFileUtils.hasFiles;
import static com.crcl.post.utils.CrclUtils.applyIf;
import static com.crcl.post.utils.CrclUtils.applyIfNotEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostQueueService queueService;
    private final UserService userService;
    private final IdpClient idpClient;
    private final StorageClient storageClient;
    private final ProfileClient profileClient;
    private final CommentClient commentClient;
    private final PostMapper mapper;
    private final FileMapperRegistry fileMapperRegistry;

    @Override
    public PostDto save(PostDto postDto) {
        Post save = postRepository.save(mapper.toEntity(postDto));
        return mapper.toDto(save);
    }

    @Override
    public PostDto save(@ValidCreatePostRequest CreatePostRequest request) {
        final var post = mapper.toEntity(request);

        applyIf(hasFiles(request.getFiles()), () -> addFiles(request, post));
        applyIfNotEmpty(request.getSharedWithUsers(), () -> addSharedWithUsers(request, post));
        applyIfNotEmpty(request.getTags(), () -> addPostTags(request, post));
        applyIfNotEmpty(request.getTaggedUsers(), () -> addTaggedUsers(request, post));

        final var username = userService.getCurrentUser().getUsername();
        ProfileDto userProfile = profileClient.findByUsername(username);
        post.setCreator(userProfile);

        final var saved = postRepository.save(post);
        final var storedPost = mapper.toDto(saved);
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
                .map(mapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<PostDto> findAll() {
        return this.postRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Page<PostDto> findAll(Pageable pageable) {
        final Page<Post> posts = postRepository.findAll(pageable);
        final List<String> ids = posts.get()
                .map(Post::getId)
                .toList();
        final Map<String, Long> countByPosts = commentClient.countByPosts(ids);

        return posts.map(mapper::toDto)
                .map(postDto -> postDto.setCommentCount(Math.toIntExact(countByPosts.getOrDefault(postDto.getId(), 0L))));
    }

    @Override
    public PostDto update(PostDto postDto, String entityId) {

        return postRepository.findById(entityId)
                .map(post -> mapper.toEntity(postDto))
                .map(postRepository::save)
                .map(mapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    private void addSharedWithUsers(CreatePostRequest request, Post post) {
        final var users = idpClient.findByUsername(new LinkedHashSet<>(request.getSharedWithUsers()));
        post.setSharedWithUsers(new LinkedHashSet<>(users));
    }

    private void addTaggedUsers(CreatePostRequest request, Post post) {
        final var taggedUsers = this.idpClient.findByUsername(new LinkedHashSet<>(request.getTaggedUsers()));
        final var usersTags = taggedUsers.stream()
                .map(userDto -> new Tag().setName(userDto.getUsername()))
                .collect(Collectors.toSet());
        post.getTags().addAll(usersTags);
    }

    private void addPostTags(CreatePostRequest request, Post post) {
        final var names = request.getTags().stream()
                .filter(Tag::isSystem)
                .map(Tag::getName)
                .toList();
        final var tags = tagRepository.findByNameIn(names);
        post.setTags(new LinkedHashSet<>(tags));
    }

    private void addFiles(CreatePostRequest request, Post post) {
        final var results = storageClient.saveAll(request.getFiles());
        final var removables = new ArrayList<FileUploadResult>();

        results.stream()
                .filter(result -> StringUtils.containsIgnoreCase(result.getContentType(), "image"))
                .peek(removables::add)
                .map(fileMapperRegistry.getMapper(FileMapperType.IMAGE))
                .forEach(image -> post.getImages().add((Image) image));

        results.stream()
                .filter(result -> StringUtils.containsIgnoreCase(result.getContentType(), "video"))
                .peek(removables::add)
                .map(fileMapperRegistry.getMapper(FileMapperType.VIDEO))
                .forEach(video -> post.getVideos().add((Video) video));

        results.removeAll(removables);

        results.stream()
                .map(fileMapperRegistry.getMapper(FileMapperType.GENERIC))
                .forEach(file -> post.getGenericFiles().add(file));
    }


}
