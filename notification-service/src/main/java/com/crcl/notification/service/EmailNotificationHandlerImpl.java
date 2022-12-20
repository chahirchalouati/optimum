package com.crcl.notification.service;

import com.crcl.notification.dto.EmailNotificationRequest;
import com.crcl.notification.dto.NotificationRequest;
import com.crcl.notification.dto.NotificationResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailNotificationHandlerImpl extends NotificationHandler<EmailNotificationRequest> implements MailTemplateGenerator {

    @Override
    public NotificationResponse notifySync(EmailNotificationRequest request) {
        return null;
    }

    @Override
    public void notifyAsync(EmailNotificationRequest request) {

    }

    @Override
    public boolean canHandle(NotificationRequest request) {
        return request instanceof EmailNotificationRequest;
    }

    @Override
    public String generate(Map<String, Object> props) {
        return null;
    }
}
