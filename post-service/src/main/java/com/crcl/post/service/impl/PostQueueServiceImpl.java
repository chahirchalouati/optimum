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
    private final PostRepository postRepository;
    private final TagService tagService;
    private final ShareService shareService;
    private final PostMapper mapper;
    private final FilesService filesService;

    @Override
    public void publishCreatePostEvent(PostDto postDto) {
        auditService.auditPostCreated(postDto);
        notificationService.notifyCreatedPost(postDto);
    }

    @Override
    public void publishReceiveCreatePostRequestEvent(CreatePostRequest createPostRequest, Post request) {

    }

    @Override
    public void publishReceiveCreatePostRequestEvent(final SecurityContext securityContext, final CreatePostRequest request, final Post post) {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() -> {
                    log.info("publishReceiveCreatePostRequestEvent() called with: securityContext = [" + securityContext + "], request = [" + request + "], post = [" + post + "]");
                    SecurityContextHolder.setContext(securityContext);
                    filesService.handleFiles(request.getFiles(), post);
                    shareService.handleShares(request.getSharedWithUsers(), post);
                    tagService.handleTags(request.getTags(), post);
                    tagService.handleTaggedUsers(request.getTaggedUsers(), post);

                    final PostDto storedPost = mapper.toDto(this.postRepository.save(post));
                    this.publishCreatePostEvent(storedPost);
                }
        );
    }
}
