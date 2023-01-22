package com.crcl.authentication.repository;

import com.crcl.authentication.domain.FriendShip;
import com.crcl.authentication.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    public Pair<Boolean, FriendShip> hasFriendShip(String owner, String friend) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("owner.username").is(owner).and("friends.username").is(friend),
                Criteria.where("owner.username").is(friend).and("friends.username").is(owner)
        );

        Query query = Query.query(criteria);
        FriendShip friendShip = this.mongoTemplate.findOne(query, FriendShip.class);
        return Pair.of(Objects.nonNull(friendShip), Optional.ofNullable(friendShip).orElse(new FriendShip()));
    }
}
