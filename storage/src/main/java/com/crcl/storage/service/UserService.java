package com.crcl.storage.service;

import com.crcl.core.dto.UserDto;
import reactor.core.publisher.Mono;

public interface UserService {
    UserDto getCurrentUser();

    Mono<Object> details();

    String getToken();
}
