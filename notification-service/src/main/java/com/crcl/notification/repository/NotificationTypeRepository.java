package com.crcl.notification.repository;

import com.crcl.notification.domain.NotificationType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface NotificationTypeRepository extends ReactiveMongoRepository<NotificationType, String>, CustomNotificationTypeRepository {
    Mono<NotificationType> findByType(String type);
}
