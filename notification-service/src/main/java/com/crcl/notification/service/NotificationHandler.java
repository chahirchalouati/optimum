package com.crcl.notification.service;

import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.queue.NotificationQueueSender;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NotificationHandler {
    @Autowired
    protected NotificationQueueSender notificationQueueSender;

    public abstract NotificationResponse notifySync(NotificationRequest request, NotificationType type);

    public abstract void notifyAsync(NotificationRequest request, NotificationType type);

    public abstract boolean canHandle(NotificationType type);
}
