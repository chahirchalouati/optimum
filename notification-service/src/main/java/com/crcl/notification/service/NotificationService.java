package com.crcl.notification.service;


import com.crcl.common.dto.requests.NotificationRequest;
import com.crcl.common.dto.responses.NotificationResponse;
import reactor.core.publisher.Mono;

public interface NotificationService {
    Mono<NotificationResponse<?>> notify(NotificationRequest request);
}
