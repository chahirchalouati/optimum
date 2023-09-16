package com.crcl.post.service.impl;

import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.requests.AuditRequest;
import com.crcl.core.queue.QueuePublisher;
import com.crcl.core.utils.AuditAction;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.post.dto.PostDto;
import com.crcl.post.service.AuditService;
import com.crcl.post.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

@Service
@Slf4j
public class AuditServiceImpl extends QueuePublisher implements AuditService {

    private final ObjectMapper objectMapper;
    private final UserService userService;

    public AuditServiceImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, UserService userService) {
        super(rabbitTemplate);
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @Override
    public void auditPostCreated(PostDto post) {
        HashMap<String, Object> headers = new HashMap<>(1);
        headers.put("token", userService.getToken());

        final var auditRequest = new AuditRequest()
                .setAction(AuditAction.CREATE_POST_ACTION)
                .setUsername(userService.getCurrentUser().getUsername())
                .setIdentifier(UUID.randomUUID().toString())
                .setDetails(objectMapper.convertValue(post, LinkedHashMap.class));

        final var event = new DefaultQEvent<AuditRequest>()
                .withPayload(auditRequest)
                .withHeaders(headers);

        this.publishMessage(event, QueueDefinition.AUDIT_MESSAGE_QUEUE);
    }
}
