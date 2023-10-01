package com.crcl.post.service.impl;

import com.crcl.core.dto.queue.CreatePostPayload;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.requests.AuditEventPayload;
import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.core.utils.*;
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
import java.util.Map;
import java.util.UUID;
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
    private final PostRepository postRepository;
    private final EventPayloadMapper eventPayloadMapper;
    private final EventQueuePublisher queuePublisher;

    @Override
    public void processPostAsync(SecurityContext securityContext, CreatePostRequest request, Post post) {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.execute(() -> {
                log.debug("Received create post request. Security context: [{}], Request details: [{}], Post details: [{}]",
                        securityContext, request, post);
                try {
                    SecurityContextHolder.setContext(securityContext);
                    var fileUploadResults = filesService.handleFiles(request.getFiles(), post);
                    shareService.handleShares(request.getSharedWithUsers(), post);
                    tagService.processTags(request.getTags(), post);
                    final var storedPost = this.postRepository.save(post);
                    this.publishPostCreatedEvent(request, fileUploadResults);
                    this.publishAuditEvent(request, storedPost);
                } catch (Exception e) {
                    log.error("An error occurred while processing the create post request: {}", e.getMessage(), e);
                } finally {
                    SecurityContextHolder.clearContext();
                }
            });
        }
    }

    private void publishAuditEvent(final CreatePostRequest createPostRequest, final Post post) {
        final var createPostPayload = eventPayloadMapper.toCreatePostPayload(createPostRequest);
        final var username = this.userService.getCurrentUser().getUsername();
        final var details = Map.of(KeyDefinition.CREATE_POST, createPostPayload, KeyDefinition.POST, post);
        final var payload = new AuditEventPayload()
                .setAction(AuditAction.ACTION_CREATE)
                .setIdentifier(UUID.randomUUID().toString())
                .setUsername(username)
                .setDetails(details);

        final var event = new DefaultQEvent<>(payload);
        this.queuePublisher.publishMessage(event, QueueDefinition.AUDIT_MESSAGE_QUEUE);
    }

    private void publishPostCreatedEvent(final CreatePostRequest createPostRequest, List<FileUploadResult> files) {
        final var payload = eventPayloadMapper.toCreatePostPayload(createPostRequest);
        payload.setFiles(files);
        final var authenticatedQEvent = new AuthenticatedQEvent<CreatePostPayload>()
                .withPayload(payload)
                .withToken(userService.getToken());

        this.queuePublisher.publishAuthenticatedMessage(
                authenticatedQEvent,
                ExchangeDefinition.PostExchange.CREATE,
                RoutingKeyDefinition.EMPTY);
    }
}
