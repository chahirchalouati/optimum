package com.crcl.notification.queue;

import com.crcl.core.queue.QueuePublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service("notificationQueuePublisher")
public class NotificationQueuePublisher extends QueuePublisher {

    public NotificationQueuePublisher(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }
}
