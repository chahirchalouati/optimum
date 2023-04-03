package com.crcl.notification.service;

import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailNotificationHandlerImpl extends NotificationHandler implements MailTemplateGenerator {

    @Override
    public NotificationResponse notifySync(NotificationRequest request) {
        return null;
    }

    @Override
    public void notifyAsync(NotificationRequest request) {

    }

    @Override
    public boolean canHandle(NotificationRequest request) {
        return request.getType().isEmail();
    }

    @Override
    public String generate(Map<String, Object> props) {
        return null;
    }
}
