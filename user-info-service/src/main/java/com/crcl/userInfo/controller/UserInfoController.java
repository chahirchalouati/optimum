package com.crcl.userInfo.controller;


import com.crcl.userInfo.domain.UserInfo;
import com.crcl.userInfo.service.UserInfoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("users")
@AllArgsConstructor
@PreAuthorize("hasAuthority('SCOPE_service')")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/{userId}")
    public Mono<UserInfo> findById(@PathVariable String userId) {
        return userInfoService.findById(userId);
    }

    @GetMapping
    public Mono<Page<UserInfo>> findAll(Pageable pageable) {
        return userInfoService.findAll(pageable);
    }

    @PostMapping
    public Mono<UserInfo> save(@Valid @RequestBody UserInfo userInfo) {
        return userInfoService.save(userInfo);
    }

    @PutMapping("/{userId}")
    public Mono<UserInfo> update(@Valid @RequestBody UserInfo userInfo, @PathVariable String userId) {
        return userInfoService.update(userInfo, userId);
    }

    @DeleteMapping("/{userId}")
    public Mono<Void> deleteById(@PathVariable String userId) {
        return userInfoService.deleteById(userId);
    }
}
