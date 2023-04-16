package com.crcl.notification.service;

import com.crcl.common.dto.UserDto;
import com.crcl.common.dto.queue.QEvent;
import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import com.crcl.common.queue.QueuePublisher;
import com.crcl.common.utils.NotificationTargets;
import com.crcl.notification.client.SrvIdpClient;
import com.crcl.notification.domain.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@Slf4j
public class EmailNotificationHandlerImpl extends NotificationHandler {
    private final MailTemplateGenerator templateGenerator;
    private final MailService mailService;
    private final SrvIdpClient srvIdpClient;

    public EmailNotificationHandlerImpl(QueuePublisher notificationQueuePublisher, MailTemplateGenerator templateGenerator, MailService mailService, SrvIdpClient srvIdpClient) {
        super(notificationQueuePublisher);
        this.templateGenerator = templateGenerator;
        this.mailService = mailService;
        this.srvIdpClient = srvIdpClient;
    }

    @Override
    public NotificationResponse notifySync(NotificationRequest request, NotificationType type) {
        String mailContent = templateGenerator.generate((LinkedHashMap<String, Object>) request.getPayload(), type.getTemplateId());
        if (type.getNotificationTargets() == NotificationTargets.All_FRIENDS) {
            Page<UserDto> friends = srvIdpClient.findFriends(request.getSender(), PageRequest.ofSize(100)).block();
            do {
                assert friends != null;
                friends.forEach(userDto -> mailService.send(mailContent, new String[]{userDto.getEmail()}, null, type.getSubject()));
            } while (!friends.isLast());
        } else {
            type.getTargets().forEach(target -> mailService.send(mailContent, new String[]{target}, null, type.getSubject()));
        }

        NotificationResponse<Object> response = new NotificationResponse<>();
        response.setPayload(request.getPayload());
        return response;
    }

    @Override
    public void notifyAsync(QEvent<NotificationRequest> request, NotificationType type) {
    }

    @Override
    public boolean canHandle(NotificationType type) {
        return type.isEmail();
    }
}
