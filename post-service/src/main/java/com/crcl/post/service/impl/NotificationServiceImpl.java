package com.crcl.post.service.impl;

import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.utils.NotificationDefinition;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.post.dto.PostDto;
import com.crcl.post.service.NotificationService;
import com.crcl.post.service.QueueService;
import com.crcl.post.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl extends QueueService implements NotificationService {

    public NotificationServiceImpl(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @Override
    public void notifyCreatedPost(PostDto payload) {
        var request = new NotificationRequest<PostDto>()
                .setSender(payload.getCreator().getUser().getUsername())
                .setType(NotificationDefinition.NOTIFY_POST_CREATED)
                .setPayload(payload);
        rabbitTemplate.convertAndSend(QueueDefinition.NOTIFY_POST_CREATED_QUEUE, request);
    }
}
