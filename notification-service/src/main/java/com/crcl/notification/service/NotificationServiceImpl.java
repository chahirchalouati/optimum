package com.crcl.notification.service;

import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final List<NotificationHandler> handlers;

    public NotificationServiceImpl(List<NotificationHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public Mono notify(NotificationRequest request) {
        List<NotificationResponse> responses = new ArrayList<>();
        handlers.stream()
                .filter(notificationHandler -> notificationHandler.canHandle(request))
                .forEach(notificationHandler -> {
                    if (request.isAsync()) {
                        log.info("START processing async notification" + request.getId().toString() + " using handler :" + notificationHandler.getClass().getName());
                        notificationHandler.notifyAsync(request);
                    } else {
                        log.info("START processing sync notification" + request.getId().toString() + " using handler :" + notificationHandler.getClass().getName());
                        NotificationResponse response = notificationHandler.notifySync(request);
                        responses.add(response);
                    }
                });

        return Mono.just(responses);
    }
}
