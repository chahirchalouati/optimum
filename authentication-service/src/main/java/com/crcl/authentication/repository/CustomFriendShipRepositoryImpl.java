package com.crcl.authentication.repository;

import com.crcl.authentication.domain.FriendShip;
import com.crcl.authentication.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
@AllArgsConstructor
public class CustomFriendShipRepositoryImpl implements CustomFriendShipRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public FriendShip add(User owner, List<User> friends) {
        var friendShip = new FriendShip();
        friendShip.setFriends(new HashSet<>(friends));
        friendShip.setOwner(owner);
        return mongoTemplate.save(friendShip);
    }

    @Override
    public FriendShip remove(User owner, User newFriend) {

        return null;
    }
}
