package com.crcl.comment.service;


import com.crcl.comment.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserDto getCurrentUser();

    List<UserDto> getUsersByUserName(Set<String> usersNames);
}
