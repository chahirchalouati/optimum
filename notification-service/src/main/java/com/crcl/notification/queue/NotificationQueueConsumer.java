package com.crcl.notification.queue;

import com.crcl.common.dto.queue.DefaultQEvent;
import com.crcl.common.dto.requests.NotificationRequest;

public interface NotificationQueueConsumer {
    void consume(DefaultQEvent<NotificationRequest> request);
}
