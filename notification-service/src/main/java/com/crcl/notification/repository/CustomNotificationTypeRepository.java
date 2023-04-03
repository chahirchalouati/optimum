package com.crcl.notification.repository;

import com.crcl.notification.domain.NotificationType;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;

public interface CustomNotificationTypeRepository {
    Flux<NotificationType> findAll(Pageable pageable);
}
