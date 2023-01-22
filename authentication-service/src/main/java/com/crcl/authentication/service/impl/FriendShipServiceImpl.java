package com.crcl.authentication.service.impl;

import com.crcl.authentication.dto.FriendShipDto;
import com.crcl.authentication.dto.UserDto;
import com.crcl.authentication.service.FriendShipService;
import org.springframework.stereotype.Service;

@Service
public class FriendShipServiceImpl implements FriendShipService {

    @Override
    public FriendShipDto add(UserDto owner, UserDto newFriend) {
        return null;
    }

    @Override
    public FriendShipDto remove(UserDto owner, UserDto newFriend) {
        return null;
    }
}
