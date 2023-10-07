package com.crcl.notification.repository;

import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.domain.NotificationTypeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface CustomNotificationTypeRepository {
    Mono<Page<NotificationType>> findAll(Pageable pageable);

    Mono<Page<NotificationType>> search(NotificationTypeRequest request);
}
