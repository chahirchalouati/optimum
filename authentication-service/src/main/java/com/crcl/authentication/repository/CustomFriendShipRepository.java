package com.crcl.authentication.repository;

import com.crcl.authentication.domain.FriendShip;
import com.crcl.authentication.domain.FriendShipState;
import com.crcl.authentication.domain.User;

import java.util.Optional;

public interface CustomFriendShipRepository {
    FriendShip link(User right, User left, FriendShipState state, String initiator);

    FriendShip remove(User owner, User newFriend);

    Optional<FriendShip> hasFriendShip(String owner, String friend);


}
