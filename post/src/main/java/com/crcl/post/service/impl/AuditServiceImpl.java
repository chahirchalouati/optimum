package com.crcl.post.service.impl;

import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.requests.AuditEventPayload;
import com.crcl.core.queue.QueuePublisher;
import com.crcl.core.utils.AuditAction;
import com.crcl.core.utils.KeyDefinition;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.mapper.EventPayloadMapper;
import com.crcl.post.service.AuditService;
import com.crcl.post.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class AuditServiceImpl extends QueuePublisher implements AuditService {

    private final UserService userService;
    private final EventPayloadMapper eventPayloadMapper;

    public AuditServiceImpl(UserService userService, EventPayloadMapper eventPayloadMapper, RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
        this.userService = userService;
        this.eventPayloadMapper = eventPayloadMapper;
    }

    @Override
    public void auditPostCreated(final CreatePostRequest createPostRequest, final Post post) {
        final var createPostPayload = eventPayloadMapper.toCreatePostPayload(createPostRequest);
        final var username = this.userService.getCurrentUser().getUsername();
        final var details = Map.of(KeyDefinition.CREATE_POST, createPostPayload, KeyDefinition.POST, post);
        final var payload = new AuditEventPayload()
                .setAction(AuditAction.ACTION_CREATE)
                .setIdentifier(UUID.randomUUID().toString())
                .setUsername(username)
                .setDetails(details);

        final var event = new DefaultQEvent<>(payload);
        this.publishMessage(event, QueueDefinition.AUDIT_MESSAGE_QUEUE);
    }


}
