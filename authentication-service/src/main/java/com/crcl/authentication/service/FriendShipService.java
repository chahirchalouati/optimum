package com.crcl.authentication.service;

import com.crcl.authentication.dto.FriendShipDto;
import com.crcl.authentication.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FriendShipService {
    FriendShipDto create(String owner, String newFriend);

    FriendShipDto remove(UserDto owner, UserDto newFriend);

    Page<UserDto> findFriends(String username, Pageable pageable);
}
