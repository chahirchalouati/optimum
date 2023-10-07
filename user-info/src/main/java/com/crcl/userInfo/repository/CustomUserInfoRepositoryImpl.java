package com.crcl.userInfo.repository;

import com.crcl.userInfo.domain.UserInfo;
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
public class CustomUserInfoRepositoryImpl implements CustomUserInfoRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Page<UserInfo>> findAll(Pageable pageable) {
        Query query = new Query().with(pageable);

        return mongoTemplate.find(query, UserInfo.class)
                .collectList()
                .map(notificationTypes -> new PageImpl<>(notificationTypes, pageable, notificationTypes.size()));
    }
}
