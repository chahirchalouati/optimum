package com.crcl.notification.service;

import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationHandlerImpl extends NotificationHandler {
    @Override
    public NotificationResponse notifySync(NotificationRequest request) {
        return null;
    }

    @Override
    public void notifyAsync(NotificationRequest request) {

    }

    @Override
    public boolean canHandle(NotificationRequest request) {
        return request.getType().isSms();
    }
}
