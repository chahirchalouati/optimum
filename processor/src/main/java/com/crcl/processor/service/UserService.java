package com.crcl.processor.service;

import com.crcl.core.dto.UserDto;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDto> getCurrentUser();
}
