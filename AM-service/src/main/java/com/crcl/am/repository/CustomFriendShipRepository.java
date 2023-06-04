package com.crcl.am.repository;

import com.crcl.am.domain.FriendShip;
import com.crcl.am.domain.FriendShipState;
import com.crcl.am.domain.GramifyUser;

import java.util.Optional;

public interface CustomFriendShipRepository {
    FriendShip link(GramifyUser sender, GramifyUser recipient, FriendShipState state);

    FriendShip remove(GramifyUser sender, GramifyUser recipient);

    Optional<FriendShip> areFriends(String sender, String recipient);


}
