package com.crcl.userInfo.repository;

import com.crcl.userInfo.domain.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface CustomUserInfoRepository {
    Mono<Page<UserInfo>> findAll(Pageable pageable);
}
