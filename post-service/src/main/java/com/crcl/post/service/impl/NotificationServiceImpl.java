package com.crcl.post.service.impl;

import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.requests.NotificationRequest;
import com.crcl.core.queue.QueuePublisher;
import com.crcl.core.utils.NotificationDefinition;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.post.dto.PostDto;
import com.crcl.post.service.NotificationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl extends QueuePublisher implements NotificationService {

    public NotificationServiceImpl(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @Override
    public void notifyCreatedPost(PostDto payload) {

        final var request = new NotificationRequest<PostDto>()
                .setSender(payload.getCreator().getUser().getUsername())
                .setNotificationDefinition(NotificationDefinition.NOTIFY_POST_CREATED)
                .setPayload(payload);

        final var event = new DefaultQEvent<NotificationRequest<PostDto>>()
                .withPayload(request);

        this.publishMessage(event, QueueDefinition.NOTIFY_POST_CREATED_QUEUE);
    }
}
