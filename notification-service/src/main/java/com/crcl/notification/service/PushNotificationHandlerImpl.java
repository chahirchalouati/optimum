package com.crcl.notification.service;

import com.crcl.notification.dto.NotificationRequest;
import com.crcl.notification.dto.NotificationResponse;
import com.crcl.notification.dto.PushNotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationHandlerImpl extends NotificationHandler<PushNotificationRequest> {


    @Override
    public NotificationResponse<?> notifySync(PushNotificationRequest request) {
        return null;
    }

    @Override
    public void notifyAsync(PushNotificationRequest request) {

    }

    @Override
    public boolean canHandle(NotificationRequest request) {
        return request instanceof PushNotificationRequest;
    }


}
