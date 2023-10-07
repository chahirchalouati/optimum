package com.crcl.notification.hanlder;

import com.crcl.core.dto.requests.NotificationRequest;
import com.crcl.core.dto.responses.NotificationResponse;
import com.crcl.core.queue.QueuePublisher;
import com.crcl.notification.domain.NotificationType;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationHandlerImpl extends NotificationHandler {


    public PushNotificationHandlerImpl(QueuePublisher notificationQueuePublisher) {
        super(notificationQueuePublisher);
    }

    @Override
    public NotificationResponse<?> notifySync(NotificationRequest request, NotificationType type) {
        return null;
    }

    @Override
    public void notifyAsync(NotificationRequest request, NotificationType type) {
    }

    @Override
    public boolean canHandle(NotificationType type) {
        return type.isPush();
    }


}
