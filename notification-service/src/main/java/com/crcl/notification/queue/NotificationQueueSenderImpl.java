package com.crcl.notification.queue;

import com.crcl.notification.dto.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationQueueSenderImpl implements NotificationQueueSender {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendNotification(NotificationRequest request) {
        rabbitTemplate.convertAndSend(request);
    }

}
