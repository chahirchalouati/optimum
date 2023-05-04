package com.crcl.userInfo.service;

import com.crcl.userInfo.domain.UserInfo;
import com.crcl.userInfo.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public Mono<UserInfo> save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public Flux<UserInfo> saveAll(List<UserInfo> entitiesDto) {
        return Flux.fromIterable(entitiesDto)
                .flatMap(this::save);
    }

    @Override
    public Mono<Void> deleteById(String userId) {
        return userInfoRepository.existsByUserId(userId)
                .flatMap(exists -> exists ? userInfoRepository.deleteByUserId(userId) : Mono.empty());
    }

    @Override
    public Mono<UserInfo> findById(String userId) {
        return userInfoRepository.findByUserId(userId);
    }

    @Override
    public Flux<UserInfo> findAll() {
        return Flux.fromIterable(List.of());
    }

    @Override
    public Mono<Page<UserInfo>> findAll(Pageable pageable) {
        return userInfoRepository.findAll(pageable);
    }

    @Override
    public Mono<UserInfo> update(UserInfo userInfo, String s) {
        return null;
    }
}
