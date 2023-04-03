package com.crcl.notification.queue;

import com.crcl.common.dto.requests.NotificationRequest;

public interface NotificationQueueSender {
    void sendNotification(NotificationRequest request);
}
