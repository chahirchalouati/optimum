package com.crcl.friend_ship.service.impl;

import com.crcl.core.dto.UserDto;
import com.crcl.friend_ship.clients.IdpClient;
import com.crcl.friend_ship.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final IdpClient idpClient;

    @Override
    public Mono<UserDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.error("found null authentication");
            return Mono.empty();
        }

        return idpClient.findByUsername(authentication.getName());
    }
}
