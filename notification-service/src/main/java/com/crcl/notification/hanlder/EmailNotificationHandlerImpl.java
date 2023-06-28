package com.crcl.notification.hanlder;

import com.crcl.common.dto.UserDto;
import com.crcl.common.dto.queue.events.DefaultQEvent;
import com.crcl.common.dto.queue.events.QEvent;
import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import com.crcl.common.queue.QueuePublisher;
import com.crcl.common.utils.NotificationTargets;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.notification.client.srvAuthentication;
import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.service.MailService;
import com.crcl.notification.service.MailTemplateGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Objects;

@Service
@Slf4j
public class EmailNotificationHandlerImpl extends NotificationHandler {
    private final MailTemplateGenerator templateGenerator;
    private final MailService mailService;
    private final srvAuthentication srvAuthentication;

    public EmailNotificationHandlerImpl(QueuePublisher notificationQueuePublisher, MailTemplateGenerator templateGenerator, MailService mailService, srvAuthentication srvAuthentication) {
        super(notificationQueuePublisher);
        this.templateGenerator = templateGenerator;
        this.mailService = mailService;
        this.srvAuthentication = srvAuthentication;
    }

    @Override
    public NotificationResponse notifySync(NotificationRequest request, NotificationType type) {
        String mailContent = templateGenerator.generate((LinkedHashMap<String, Object>) request.getPayload(), type.getTemplateId());
        if (type.getNotificationTargets() == NotificationTargets.All_FRIENDS) {
            Page<UserDto> friends = srvAuthentication.findFriends(request.getSender(), PageRequest.ofSize(100)).block();
            if (Objects.nonNull(friends)) {
                do {
                    friends.forEach(userDto -> mailService.send(mailContent, new String[]{userDto.getEmail()}, null, type.getSubject()));
                } while (!friends.isLast());
            }
        } else {
            type.getTargets().forEach(target -> mailService.send(mailContent, new String[]{target}, null, type.getSubject()));
        }

        NotificationResponse<Object> response = new NotificationResponse<>();
        response.setPayload(request.getPayload());
        return response;
    }

    @Override
    public void notifyAsync(NotificationRequest request, NotificationType type) {
        if (type.isAsync()) {
            QEvent<NotificationRequest> event = new DefaultQEvent<NotificationRequest>().setPayload(request);
            queuePublisher.publish(event, QueueDefinition.NOTIFY_ASYNC_QUEUE);
        }
    }

    @Override
    public boolean canHandle(NotificationType type) {
        return type.isEmail();
    }
}
