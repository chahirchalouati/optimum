package com.crcl.notification.repository;

import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.domain.NotificationTypeRequest;
import com.crcl.notification.repository.filters.NotificationTypeFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
@RequiredArgsConstructor
public class CustomNotificationTypeRepositoryImpl implements CustomNotificationTypeRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<NotificationType> findAll(Pageable pageable) {
        Query query = new Query().with(pageable);
        return mongoTemplate.find(query, NotificationType.class);
    }

    @Override
    public Mono<NotificationType> search(NotificationTypeRequest request) {
        NotificationTypeFilter filter = new NotificationTypeFilter();
        Query query = filter.filterFor(request).build();

        return mongoTemplate.findOne(query, NotificationType.class);
    }
}
