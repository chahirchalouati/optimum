package com.crcl.authentication.service;

import com.crcl.authentication.dto.CreateUserRequest;
import com.crcl.authentication.dto.UserDto;
import com.crcl.common.utils.generic.GenericService;

import java.util.Set;

public interface UserService extends GenericService<UserDto, String> {

    UserDto findByUsername(String username);

    UserDto save(CreateUserRequest request);

    UserDto getCurrentUser();

    Set<UserDto> findByUserNames(Set<String> userNames);
}

