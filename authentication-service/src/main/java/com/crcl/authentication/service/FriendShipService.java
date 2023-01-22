package com.crcl.authentication.service;

import com.crcl.authentication.dto.FriendShipDto;
import com.crcl.authentication.dto.UserDto;

public interface FriendShipService {
    FriendShipDto add(UserDto owner, UserDto newFriend);

    FriendShipDto remove(UserDto owner, UserDto newFriend);

}
