package com.crcl.authentication.repository;

import com.crcl.authentication.domain.FriendShip;
import com.crcl.authentication.domain.User;

import java.util.List;

public interface CustomFriendShipRepository {
    FriendShip add(User owner, List<User> friends);

    FriendShip remove(User owner, User newFriend);
}
