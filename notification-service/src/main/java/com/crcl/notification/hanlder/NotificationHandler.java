package com.crcl.notification.hanlder;

import com.crcl.core.dto.requests.NotificationRequest;
import com.crcl.core.dto.responses.NotificationResponse;
import com.crcl.core.queue.QueuePublisher;
import com.crcl.notification.domain.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class NotificationHandler<P, T extends NotificationResponse<P>> {

    protected final QueuePublisher queuePublisher;

    public abstract T notifySync(NotificationRequest request, NotificationType type);

    public abstract void notifyAsync(NotificationRequest request, NotificationType type);

    public abstract boolean canHandle(NotificationType type);

    public String getHandlerName() {
        return this.getClass().getName();
    }
}

