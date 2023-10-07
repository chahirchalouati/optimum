package com.crcl.post.service.impl;

import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.requests.NotificationRequest;
import com.crcl.core.queue.QueuePublisher;
import com.crcl.core.utils.NotificationDefinition;
import com.crcl.core.utils.NotificationTargets;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.post.client.ProfileClient;
import com.crcl.post.domain.Post;
import com.crcl.post.service.NotificationService;
import com.crcl.post.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl extends QueuePublisher implements NotificationService {

    private final ProfileClient profileClient;
    private final UserService userService;

    public NotificationServiceImpl(RabbitTemplate rabbitTemplate, ProfileClient profileClient, UserService userService) {
        super(rabbitTemplate);
        this.profileClient = profileClient;
        this.userService = userService;
    }

    @Override
    public void notifyCreatedPost(Post payload) {

        final String username = userService.getCurrentUser().getUsername();
        final NotificationTargets notificationTarget = profileClient.getNotificationTarget(username, NotificationDefinition.NOTIFY_POST_CREATED);
        final var request = new NotificationRequest<Post>()
                .setSender(payload.getCreator().getUser().getUsername())
                .setNotificationDefinition(NotificationDefinition.NOTIFY_POST_CREATED)
                .setNotificationTargets(notificationTarget)
                .setPayload(payload);

        final var event = new DefaultQEvent<NotificationRequest<Post>>()
                .withPayload(request);

        this.publishMessage(event, QueueDefinition.NOTIFY_SYNC_QUEUE);
    }
}
