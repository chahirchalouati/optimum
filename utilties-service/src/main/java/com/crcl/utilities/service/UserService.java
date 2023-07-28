package com.crcl.utilities.service;

import com.crcl.core.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserDto getCurrentUser();

    String getToken();

    List<UserDto> getUsersByUserName(Set<String> usersNames);
}
