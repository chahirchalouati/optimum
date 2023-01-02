package com.crcl.authentication.service;

import com.crcl.authentication.dto.UserDto;
import com.crcl.common.utils.generic.GenericService;

public interface UserService extends GenericService<UserDto, String> {

    UserDto findByUsername(String username);
}
