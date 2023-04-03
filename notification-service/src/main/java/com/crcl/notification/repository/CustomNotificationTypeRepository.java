package com.crcl.notification.repository;

import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.domain.NotificationTypeRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomNotificationTypeRepository {
    Flux<NotificationType> findAll(Pageable pageable);

    Mono<NotificationType> search(NotificationTypeRequest request);
}
