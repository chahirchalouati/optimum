package com.crcl.notification.repository;

import com.crcl.notification.domain.NotificationType;
import com.crcl.notification.domain.NotificationTypeRequest;
import com.crcl.notification.repository.filters.NotificationTypeFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CustomNotificationTypeRepositoryImpl implements CustomNotificationTypeRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Page<NotificationType>> findAll(Pageable pageable) {
        Query query = new Query().with(pageable);

        return mongoTemplate.find(query, NotificationType.class)
                .collectList()
                .map(notificationTypes -> new PageImpl<>(notificationTypes, pageable, notificationTypes.size()));
    }

    @Override
    public Mono<Page<NotificationType>> search(NotificationTypeRequest request) {
        NotificationTypeFilter notificationTypeFilter = new NotificationTypeFilter();
        Query query = notificationTypeFilter
                .filter(request)
                .build();
        query.with(request.getPageRequest());

        return mongoTemplate.find(query, NotificationType.class)
                .collectList()
                .map(notificationTypes -> new PageImpl<>(notificationTypes, request.getPageRequest(), notificationTypes.size()));
    }
}
