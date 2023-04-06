package com.crcl.notification.service;

import com.crcl.common.dto.UserDto;
import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import com.crcl.common.utils.NotificationTargets;
import com.crcl.notification.client.IdpClient;
import com.crcl.notification.domain.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationHandlerImpl extends NotificationHandler {
    private final MailTemplateGenerator templateGenerator;
    private final MailService mailService;
    private final IdpClient idpClient;

    @Override
    public NotificationResponse notifySync(NotificationRequest request, NotificationType type) {
        String mailContent = templateGenerator.generate((LinkedHashMap<String, Object>) request.getPayload(), type.getTemplateId());
        if (type.getNotificationTargets() == NotificationTargets.All_FRIENDS) {
            Page<UserDto> friends = idpClient.findFriends(request.getSender(), PageRequest.ofSize(100)).block();
            do {
                assert friends != null;
                friends.forEach(userDto -> mailService.send(mailContent, new String[]{userDto.getEmail()}, null, type.getSubject()));
            } while (!friends.isLast());
        }

        NotificationResponse<Object> response = new NotificationResponse<>();
        response.setPayload(request.getPayload());
        return response;
    }

    @Override
    public void notifyAsync(NotificationRequest request, NotificationType type) {

    }

    @Override
    public boolean canHandle(NotificationType type) {
        return type.isEmail();
    }
}
