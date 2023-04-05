package com.crcl.notification.service;

import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.repository.NotificationTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final List<NotificationHandler> handlers;
    private final NotificationTypeRepository notificationTypeRepository;

    @Override
    public Mono notify(NotificationRequest request) {
        List<NotificationResponse> responses = new ArrayList<>();
        NotificationType notificationType = notificationTypeRepository.findByType(request.getType()).block();

        handlers.stream()
                .filter(notificationHandler -> notificationHandler.canHandle(notificationType))
                .forEach(notificationHandler -> {
                    if (notificationType.isAsync()) {
                        log.info("START processing async notification" + request.getId().toString() + " using handler :" + notificationHandler.getClass().getName());
                        notificationHandler.notifyAsync(request, notificationType);
                    } else {
                        log.info("START processing sync notification" + request.getId().toString() + " using handler :" + notificationHandler.getClass().getName());
                        NotificationResponse response = notificationHandler.notifySync(request, notificationType);
                        responses.add(response);
                    }
                });

        return Mono.just(responses);

    }
}
