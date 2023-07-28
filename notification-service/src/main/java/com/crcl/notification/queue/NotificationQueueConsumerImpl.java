package com.crcl.notification.queue;

import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.requests.NotificationRequest;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationQueueConsumerImpl implements NotificationQueueConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = QueueDefinition.NOTIFY_POST_CREATED_QUEUE)
    public void consume(DefaultQEvent<NotificationRequest> request) {
        var event = request.getPayload();
        this.notificationService.notify(event);
    }
}
