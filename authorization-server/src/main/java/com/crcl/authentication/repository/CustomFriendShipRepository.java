package com.crcl.authentication.repository;

import com.crcl.authentication.domain.FriendShip;
import com.crcl.authentication.domain.FriendShipState;
import com.crcl.authentication.domain.GramifyUser;

import java.util.Optional;

public interface CustomFriendShipRepository {
    FriendShip link(GramifyUser sender, GramifyUser recipient, FriendShipState state);

    FriendShip remove(GramifyUser sender, GramifyUser recipient);

    Optional<FriendShip> areFriends(String sender, String recipient);


}
