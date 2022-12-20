package com.crcl.notification.service;

import com.crcl.notification.dto.NotificationRequest;
import com.crcl.notification.dto.NotificationResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final List<NotificationHandler> handlers;

    public NotificationServiceImpl(List<NotificationHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public Mono<NotificationResponse<?>> notify(NotificationRequest request) {
        var handler = handlers.stream()
                .filter(notificationHandler -> notificationHandler.canHandle(request))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to find notification handler ..."));
        if (request.isAsync()) {
            handler.notifyAsync(request);
        } else {
            handler.notifySync(request);
        }
        return Mono.empty();
    }
}
