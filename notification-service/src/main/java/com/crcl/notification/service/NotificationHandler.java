package com.crcl.notification.service;

import com.crcl.notification.dto.NotificationRequest;
import com.crcl.notification.dto.NotificationResponse;
import com.crcl.notification.queue.NotificationQueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class NotificationHandler<T extends NotificationRequest> {
    @Autowired
    private NotificationQueueSender notificationQueueSender;
    @Autowired
    private JavaMailSender javaMailSender;

    public abstract NotificationResponse notifySync(T request);

    public abstract void notifyAsync(T request);

    public abstract boolean canHandle(NotificationRequest request);
}
