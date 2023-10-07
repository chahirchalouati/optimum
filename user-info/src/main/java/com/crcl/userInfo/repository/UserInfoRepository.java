package com.crcl.userInfo.repository;

import com.crcl.userInfo.domain.UserInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserInfoRepository extends ReactiveMongoRepository<UserInfo, String>, CustomUserInfoRepository {

    Mono<Boolean> existsByUserId(String userId);

    Mono<UserInfo> findByUserId(String userId);

    Mono<Void> deleteByUserId(String userId);
}
