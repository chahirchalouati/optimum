package com.crcl.notification.service;

import com.crcl.notification.dto.NotificationRequest;
import com.crcl.notification.dto.NotificationResponse;
import reactor.core.publisher.Mono;

public interface NotificationService {
    Mono<NotificationResponse<?>> notify(NotificationRequest request);
}
