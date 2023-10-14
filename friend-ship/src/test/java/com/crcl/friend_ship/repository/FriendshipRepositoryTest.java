package com.crcl.friend_ship.repository;

import com.crcl.friend_ship.service.BaseRepositoryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public class FriendshipRepositoryTest extends BaseRepositoryConfiguration {
    @Autowired
    private final FriendShipRepository friendShipRepository;

    FriendshipRepositoryTest(FriendShipRepository friendShipRepository) {
        super();
        this.friendShipRepository = friendShipRepository;

    }
}