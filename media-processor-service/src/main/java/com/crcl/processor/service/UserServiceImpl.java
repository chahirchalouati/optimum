package com.crcl.processor.service;

import com.crcl.common.dto.UserDto;
import com.crcl.processor.clients.IdpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


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

        var userDetails = (Jwt) authentication.getPrincipal();
        return idpClient.findByUsername((String) userDetails.getHeaders().get("username"));
    }
}
