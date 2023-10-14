package com.crcl.friend_ship.service;

import com.crcl.core.dto.UserDto;
import reactor.core.publisher.Mono;

public interface UserService {
    UserDto getCurrentUser();

    Mono<Object> details();

    String getToken();
}
