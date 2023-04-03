package com.crcl.notification.service;

import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import com.crcl.notification.queue.NotificationQueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class NotificationHandler {
    @Autowired
    private NotificationQueueSender notificationQueueSender;
    @Autowired
    private JavaMailSender javaMailSender;

    public abstract NotificationResponse notifySync(NotificationRequest request);

    public abstract void notifyAsync(NotificationRequest request);

    public abstract boolean canHandle(NotificationRequest request);
}
