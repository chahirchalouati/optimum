package com.crcl.post.service.impl;

import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.dto.PostDto;
import com.crcl.post.mapper.PostMapper;
import com.crcl.post.repository.PostRepository;
import com.crcl.post.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostQueueServiceImpl implements PostQueueService {

    private final NotificationService notificationService;
    private final AuditService auditService;
    private final TagService tagService;
    private final ShareService shareService;
    private final FilesService filesService;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public void publishCreatePostEvent(PostDto postDto) {
        auditService.auditPostCreated(postDto);
        notificationService.notifyCreatedPost(postDto);
    }

    @Override
    public void publishReceiveCreatePostRequestEvent(CreatePostRequest createPostRequest, Post request) {

    }

    @Override
    public void publishReceiveCreatePostRequestEvent(final SecurityContext securityContext,
                                                     final CreatePostRequest request,
                                                     final Post post) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
            executorService.execute(() -> {
                log.debug("Received create post request. Security context: [{}], Request details: [{}], Post details: [{}]",
                        securityContext, request, post);
                try {
                    SecurityContextHolder.setContext(securityContext);
                    filesService.handleFiles(request.getFiles(), post);
                    shareService.handleShares(request.getSharedWithUsers(), post);
                    tagService.processTags(request.getTags(), post);
                    final PostDto storedPost = postMapper.toDto(this.postRepository.save(post));
                    this.publishCreatePostEvent(storedPost);
                } catch (Exception e) {
                    log.error("An error occurred while processing the create post request: {}", e.getMessage(), e);
                }
            });
        }
    }


}
