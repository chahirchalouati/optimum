package com.crcl.post.service;

import com.crcl.common.dto.queue.DefaultQEvent;
import com.crcl.common.dto.queue.QEvent;
import com.crcl.common.dto.requests.AuditRequest;
import com.crcl.common.queue.QueuePublisher;
import com.crcl.common.utils.AuditAction;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.post.dto.PostDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.MessageHeaders;
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
        AuditRequest auditRequest = new AuditRequest()
                .setAction(AuditAction.CREATE_POST_ACTION)
                .setUsername(userService.getCurrentUser().getUsername())
                .setIdentifier(UUID.randomUUID().toString())
                .setDetails(objectMapper.convertValue(post, LinkedHashMap.class));

        QEvent<AuditRequest> event = new DefaultQEvent<>();
        event.setHeaders(new MessageHeaders(headers));
        event.setPayload(auditRequest);

        this.publish(event, QueueDefinition.AUDIT_MESSAGE_QUEUE);
    }
}
