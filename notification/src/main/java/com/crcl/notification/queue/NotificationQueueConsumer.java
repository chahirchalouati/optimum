package com.crcl.notification.queue;

import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.requests.NotificationRequest;

public interface NotificationQueueConsumer {
    void consume(DefaultQEvent<NotificationRequest> request);
}
