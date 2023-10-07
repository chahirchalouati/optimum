package com.crcl.notification.hanlder;

import com.crcl.core.dto.UserDto;
import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.requests.NotificationRequest;
import com.crcl.core.dto.responses.NotificationResponse;
import com.crcl.core.queue.QueuePublisher;
import com.crcl.core.utils.NotificationTargets;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.notification.client.SrvAuthentication;
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
    private final SrvAuthentication srvAuthentication;

    public EmailNotificationHandlerImpl(final QueuePublisher notificationQueuePublisher,
                                        final MailTemplateGenerator templateGenerator,
                                        final MailService mailService,
                                        final SrvAuthentication srvAuthentication) {
        super(notificationQueuePublisher);
        this.templateGenerator = templateGenerator;
        this.mailService = mailService;
        this.srvAuthentication = srvAuthentication;
    }

    @Override
    public NotificationResponse notifySync(NotificationRequest request, NotificationType type) {
        String mailContent = templateGenerator.generate((LinkedHashMap<String, Object>) request.getPayload(),
                type.getTemplateId());
        if (request.getNotificationTargets() == NotificationTargets.All_FRIENDS) {
            Page<UserDto> friends = srvAuthentication.findFriends(request.getSender(),
                    PageRequest.ofSize(100)).block();
            if (Objects.nonNull(friends)) {
                do {
                    friends.forEach(userDto -> mailService.send(mailContent, new String[]{userDto.getEmail()}, null, type.getSubject()));
                } while (!friends.isLast());
            }
        } else {
            request.getTargets().forEach(target -> mailService.send(mailContent, new String[]{(String) target}, null, type.getSubject()));
        }

        NotificationResponse<Object> response = new NotificationResponse<>();
        response.setPayload(request.getPayload());
        return response;
    }

    @Override
    public void notifyAsync(NotificationRequest request, NotificationType type) {
        if (type.isAsync()) {
            var event = new DefaultQEvent<NotificationRequest>()
                    .withPayload(request);
            queuePublisher.publishMessage(event, QueueDefinition.NOTIFY_ASYNC_QUEUE);
        }
    }

    @Override
    public boolean canHandle(NotificationType type) {
        return type.isEmail();
    }
}
