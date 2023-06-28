package com.crcl.notification.hanlder;

import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import com.crcl.common.queue.QueuePublisher;
import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.hanlder.NotificationHandler;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationHandlerImpl extends NotificationHandler {
    public SmsNotificationHandlerImpl(QueuePublisher notificationQueuePublisher) {
        super(notificationQueuePublisher);
    }

    @Override
    public NotificationResponse notifySync(NotificationRequest request, NotificationType type) {
        return null;
    }

    @Override
    public void notifyAsync(NotificationRequest request, NotificationType type) {

    }

    @Override
    public boolean canHandle(NotificationType type) {
        return type.isSms();
    }
}
