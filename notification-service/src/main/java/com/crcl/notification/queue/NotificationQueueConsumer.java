package com.crcl.notification.queue;

import com.crcl.common.dto.requests.NotificationRequest;
import org.springframework.messaging.Message;

public interface NotificationQueueConsumer {
    void consume(Message<NotificationRequest> request);
}
