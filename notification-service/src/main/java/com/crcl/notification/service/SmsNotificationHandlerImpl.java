package com.crcl.notification.service;

import com.crcl.notification.dto.NotificationRequest;
import com.crcl.notification.dto.NotificationResponse;
import com.crcl.notification.dto.SmsNotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationHandlerImpl extends NotificationHandler<SmsNotificationRequest> {
    @Override
    public NotificationResponse notifySync(SmsNotificationRequest request) {
        return null;
    }

    @Override
    public void notifyAsync(SmsNotificationRequest request) {

    }

    @Override
    public boolean canHandle(NotificationRequest request) {
        return request instanceof SmsNotificationRequest;
    }
}
