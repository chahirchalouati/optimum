package com.crcl.post.service.impl;

import com.crcl.core.dto.ProfileDto;
import com.crcl.core.exceptions.EntityNotFoundException;
import com.crcl.post.client.CommentClient;
import com.crcl.post.client.ProfileClient;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.dto.PostDto;
import com.crcl.post.mapper.PostMapper;
import com.crcl.post.repository.PostRepository;
import com.crcl.post.service.PostProcessor;
import com.crcl.post.service.PostService;
import com.crcl.post.service.UserService;
import com.crcl.post.utils.PublishStateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.crcl.post.utils.CrclUtils.applyIfNotNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostProcessor postProcessor;
    private final UserService userService;
    private final ProfileClient profileClient;
    private final CommentClient commentClient;
    private final PostMapper postMapper;
    @Override
    public PostDto save(final PostDto postDto) {
        final Post savedPost = postRepository.save(postMapper.toEntity(postDto));
        return postMapper.toDto(savedPost);
    }
    @Override
    public PostDto save(final CreatePostRequest createPostRequest) {

        final Post post = postMapper.toEntity(createPostRequest);
        final String username = this.userService.getCurrentUser().getUsername();
        final ProfileDto userProfile = this.profileClient.findByUsername(username);
        applyIfNotNull(userProfile, post::setCreator);
        PublishStateUtils.markInProgress(post);
        final var storedPost = this.postRepository.save(post);
        postProcessor.processPostAsync(createPostRequest, storedPost);
        return postMapper.toDto(storedPost);
    }
    @Override
    public List<PostDto> saveAll(final List<PostDto> postDtoList) {
        return postDtoList.stream().map(this::save).toList();
    }

    @Override
    public void deleteById(final String postId) {
        postRepository.findById(postId)
                .ifPresentOrElse(post -> postRepository.deleteById(postId), EntityNotFoundException::new);
    }
    @Override
    public PostDto findById(final String postId) {
        return postRepository.findById(postId)
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
    public Page<PostDto> findAll(final Pageable pageable) {
        final Page<Post> postsPage = postRepository.findAll(pageable);
        final List<String> postIds = postsPage.get()
                .map(Post::getId)
                .toList();
        final Map<String, Long> countByPosts = commentClient.countByPosts(postIds);

        return postsPage.map(postMapper::toDto)
                .map(postDto -> postDto.setCommentCount(Math.toIntExact(countByPosts.getOrDefault(postDto.getId(), 0L))));
    }

    @Override
    public PostDto update(PostDto postDto, String postId) {

        return postRepository.findById(postId)
                .map(post -> postMapper.toEntity(postDto))
                .map(postRepository::save)
                .map(postMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }


}
