package com.crcl.notification.queue;

import com.crcl.notification.dto.NotificationRequest;

public interface NotificationQueueSender {
    void sendNotification(NotificationRequest request);
}
