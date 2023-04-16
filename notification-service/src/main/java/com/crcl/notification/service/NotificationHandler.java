package com.crcl.notification.service;

import com.crcl.common.dto.queue.QEvent;
import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import com.crcl.common.queue.QueuePublisher;
import com.crcl.notification.domain.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class NotificationHandler {
    protected final QueuePublisher notificationQueuePublisher;

    public abstract NotificationResponse notifySync(NotificationRequest request, NotificationType type);

    public abstract void notifyAsync(QEvent<NotificationRequest> request, NotificationType type);

    public abstract boolean canHandle(NotificationType type);
}

