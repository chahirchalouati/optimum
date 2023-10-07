package com.crcl.notification.service;


import com.crcl.core.dto.requests.NotificationRequest;
import com.crcl.core.dto.responses.NotificationResponse;
import reactor.core.publisher.Mono;

public interface NotificationService {
    Mono<NotificationResponse<?>> notify(NotificationRequest request);
}
