package com.crcl.post.service.impl;

import com.crcl.core.dto.queue.payloads.CreatePostPayload;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.core.utils.ExchangeDefinition;
import com.crcl.core.utils.RoutingKeyDefinition;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.mapper.EventPayloadMapper;
import com.crcl.post.queue.EventQueuePublisher;
import com.crcl.post.repository.PostRepository;
import com.crcl.post.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostProcessorImpl implements PostProcessor {

    private final TagService tagService;
    private final ShareService shareService;
    private final FilesService filesService;
    private final UserService userService;
    private final AuditService auditService;
    private final NotificationService notificationService;
    private final PostRepository postRepository;
    private final EventPayloadMapper eventPayloadMapper;
    private final EventQueuePublisher queuePublisher;

    @Override
    public void processPostAsync(CreatePostRequest request, Post post) {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.execute(() -> {
                log.debug("Received create post request. Security context: [{}], Request details: [{}], Post details: [{}]",
                        securityContext, request, post);
                try {
                    SecurityContextHolder.setContext(securityContext);
                   final var fileUploadResults = filesService.handleFiles(request.getFiles(), post);
                    shareService.handleShares(request.getSharedWithUsers(), post);
                    tagService.processTags(request.getTags(), post);
                    final var storedPost = this.postRepository.save(post);
                    this.publishPostCreatedEvent(request, fileUploadResults);
                    this.auditService.auditPostCreated(request, storedPost);
                    this.notificationService.notifyCreatedPost(storedPost);
                } catch (Exception e) {
                    log.error("An error occurred while processing the create post request: {}", e.getMessage(), e);
                } finally {
                    SecurityContextHolder.clearContext();
                }
            });
        }
    }


    private void publishPostCreatedEvent(final CreatePostRequest createPostRequest, List<FileUploadResult> files) {
        final var payload = eventPayloadMapper.toCreatePostPayload(createPostRequest, files);
        final var authenticatedQEvent = new AuthenticatedQEvent<CreatePostPayload>()
                .withPayload(payload)
                .withToken(userService.getToken());

        this.queuePublisher.publishAuthenticatedMessage(
                authenticatedQEvent,
                ExchangeDefinition.PostExchange.CREATE,
                RoutingKeyDefinition.EMPTY);
    }
}
