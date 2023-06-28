package com.crcl.notification.service;

import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.hanlder.NotificationHandler;
import com.crcl.notification.repository.NotificationTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final List<NotificationHandler> handlers;
    private final NotificationTypeRepository notificationTypeRepository;

    @NotNull
    private static Function<NotificationHandler, List<NotificationResponse>> handleNotificationRequest(
            NotificationRequest request,
            List<NotificationResponse> responses,
            NotificationType type) {

        return handler -> {

            if (type.isAsync()) {
                log.info("START processing async notification" + request.getId().toString() + " using handler :" + handler.getHandlerName());
                handler.notifyAsync(request, type);
            } else {
                log.info("START processing sync notification" + request.getId().toString() + " using handler :" + handler.getHandlerName());
                NotificationResponse<?> response = handler.notifySync(request, type);
                responses.add(response);
            }

            return responses;
        };
    }

    @Override
    public Mono notify(NotificationRequest request) {
        List<NotificationResponse> responses = new ArrayList<>();
        Mono<NotificationType> notificationType = notificationTypeRepository.findByType(request.getNotificationDefinition());

        return notificationType.mapNotNull(
                type -> handlers.stream()
                        .filter(notificationHandler -> notificationHandler.canHandle(type))
                        .map(handleNotificationRequest(request, responses, type))

        );
    }
}
