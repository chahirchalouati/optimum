package com.crcl.notification.repository;

import com.crcl.common.utils.NotificationDefinition;
import com.crcl.notification.domain.NotificationType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface NotificationTypeRepository extends ReactiveMongoRepository<NotificationType, String>, CustomNotificationTypeRepository {
    Mono<NotificationType> findByType(NotificationDefinition type);
}
