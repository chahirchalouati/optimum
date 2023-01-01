package com.crcl.authenticationservice.service;

import com.crcl.authenticationservice.dto.UserDto;
import com.crcl.common.utils.generic.GenericService;

public interface UserService extends GenericService<UserDto, String> {

    UserDto findByUsername(String username);
}
