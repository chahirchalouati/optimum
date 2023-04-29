package com.crcl.authentication.repository;

import com.crcl.authentication.domain.FriendShip;
import com.crcl.authentication.domain.FriendShipState;
import com.crcl.authentication.domain.User;

import java.util.Optional;

public interface CustomFriendShipRepository {
    FriendShip link(User sender, User recipient, FriendShipState state);

    FriendShip remove(User sender, User recipient);

    Optional<FriendShip> areFriends(String sender, String recipient);


}
