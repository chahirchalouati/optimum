package com.crcl.notification.queue;

import com.crcl.common.dto.queue.events.DefaultQEvent;
import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.utils.QueueDefinition;
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
