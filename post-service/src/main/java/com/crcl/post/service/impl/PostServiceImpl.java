package com.crcl.post.service.impl;

import com.crcl.core.dto.ProfileDto;
import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.core.exceptions.EntityNotFoundException;
import com.crcl.post.client.CommentClient;
import com.crcl.post.client.IdpClient;
import com.crcl.post.client.ProfileClient;
import com.crcl.post.client.StorageClient;
import com.crcl.post.domain.FileMapperType;
import com.crcl.post.domain.Image;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Video;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.dto.PostDto;
import com.crcl.post.mapper.FileMapperRegistry;
import com.crcl.post.mapper.PostMapper;
import com.crcl.post.repository.PostRepository;
import com.crcl.post.service.*;
import com.crcl.post.utils.PublishStateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.crcl.post.utils.CrclFileUtils.hasFiles;
import static com.crcl.post.utils.CrclUtils.applyIf;
import static com.crcl.post.utils.CrclUtils.applyIfNotEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TagService tagService;
    private final ShareService shareService;
    private final PostQueueService queueService;
    private final UserService userService;
    private final ProfileClient profileClient;
    private final CommentClient commentClient;
    private final PostMapper mapper;

    private final FilesService filesService;

    @Override
    public PostDto save(PostDto postDto) {
        Post save = postRepository.save(mapper.toEntity(postDto));
        return mapper.toDto(save);
    }

    @Override
    public PostDto save(CreatePostRequest request) {
        final var post = mapper.toEntity(request);

        applyIf(hasFiles(request.getFiles()), () -> filesService.handleFiles(request, post));
        applyIfNotEmpty(request.getSharedWithUsers(), () -> shareService.handleShares(request, post));
        applyIfNotEmpty(request.getTags(), () -> tagService.handleTags(request, post));
        applyIfNotEmpty(request.getTaggedUsers(), () -> tagService.handleTaggedUsers(request, post));

        final var username = userService.getCurrentUser().getUsername();
        ProfileDto userProfile = profileClient.findByUsername(username);
        post.setCreator(userProfile);
        PublishStateUtils.markInProgress(post);

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

    private void addPostTags(CreatePostRequest request, Post post) {

    }
}
