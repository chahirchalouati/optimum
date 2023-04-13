package com.crcl.post.service.impl;

import com.crcl.common.dto.queue.DefaultQEvent;
import com.crcl.common.dto.queue.QEvent;
import com.crcl.common.dto.requests.AuditRequest;
import com.crcl.common.utils.AuditAction;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.post.dto.PostDto;
import com.crcl.post.service.NotificationService;
import com.crcl.post.service.PostQueueService;
import com.crcl.post.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

@Service
public class PostQueueServiceImpl extends PostQueueService {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;
    private final UserService userService;

    public PostQueueServiceImpl(RabbitTemplate rabbitTemplate, UserService userService, NotificationService notificationService, ObjectMapper objectMapper, UserService userService1) {
        super(rabbitTemplate, userService);
        this.notificationService = notificationService;
        this.objectMapper = objectMapper;
        this.userService = userService1;
    }

    @Override
    public void publishCreatePostEvent(PostDto postDto) {
        var headers = new HashMap<String, Object>(1);
        headers.put("token", userService.getToken());

        AuditRequest auditRequest = new AuditRequest()
                .setAction(AuditAction.CREATE_POST_ACTION)
                .setUsername(userService.getCurrentUser().getUsername())
                .setIdentifier(UUID.randomUUID().toString())
                .setDetails(objectMapper.convertValue(postDto, LinkedHashMap.class));

        QEvent<AuditRequest> event = new DefaultQEvent<AuditRequest>()
                .setHeaders(headers)
                .setPayload(auditRequest);

        this.rabbitTemplate.convertAndSend(QueueDefinition.AUDIT_MESSAGE_QUEUE, event);
        notificationService.notifyCreatedPost(postDto);
    }
}
