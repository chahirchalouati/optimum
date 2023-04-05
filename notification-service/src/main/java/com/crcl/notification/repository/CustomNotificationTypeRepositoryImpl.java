package com.crcl.notification.repository;

import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.domain.NotificationTypeRequest;
import com.crcl.notification.repository.filters.NotificationTypeFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CustomNotificationTypeRepositoryImpl implements CustomNotificationTypeRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    public CustomNotificationTypeRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<NotificationType> findAll(Pageable pageable) {
        Query query = new Query()
                .with(pageable);
        return mongoTemplate.find(query, NotificationType.class);
    }

    @Override
    public Mono<Page<NotificationType>> search(NotificationTypeRequest request) {
        var notificationTypeFilter = new NotificationTypeFilter();
        var query = notificationTypeFilter
                .filter(request)
                .build();
        query.with(request.getPageRequest());

        return mongoTemplate.find(query, NotificationType.class)
                .collectList()
                .map(notificationTypes -> new PageImpl<>(notificationTypes, request.getPageRequest(), notificationTypes.size()));
    }
}
