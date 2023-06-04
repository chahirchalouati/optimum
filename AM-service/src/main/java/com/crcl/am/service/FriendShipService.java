package com.crcl.am.service;

import com.crcl.am.dto.FriendShipDto;
import com.crcl.am.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FriendShipService {
    FriendShipDto create(String newFriend);

    FriendShipDto remove(UserDto owner, UserDto newFriend);

    Page<UserDto> findFriends(String username, Pageable pageable);
}
