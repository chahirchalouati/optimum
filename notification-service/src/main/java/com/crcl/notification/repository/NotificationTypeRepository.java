package com.crcl.notification.repository;

import com.crcl.notification.domain.NotificationType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface NotificationTypeRepository extends ReactiveMongoRepository<NotificationType, String>, CustomNotificationTypeRepository {
}
