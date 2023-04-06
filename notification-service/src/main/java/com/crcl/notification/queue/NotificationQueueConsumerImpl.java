package com.crcl.notification.queue;

import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationQueueConsumerImpl implements NotificationQueueConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = QueueDefinition.NOTIFY_POST_CREATED_QUEUE)
    public void consume(Message<NotificationRequest> request) {
        this.notificationService.notify(request.getPayload());
    }
}
