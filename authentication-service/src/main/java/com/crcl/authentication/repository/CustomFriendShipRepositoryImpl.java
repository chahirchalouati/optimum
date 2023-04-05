package com.crcl.authentication.repository;

import com.crcl.authentication.domain.FriendShip;
import com.crcl.authentication.domain.FriendShipState;
import com.crcl.authentication.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomFriendShipRepositoryImpl implements CustomFriendShipRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public FriendShip link(User right, User left, FriendShipState state, String initiator) {
        var friendShip = new FriendShip();
        friendShip.setRight(right);
        friendShip.setLeft(left);
        friendShip.setState(state);
        friendShip.setInitiator(initiator);

        return mongoTemplate.save(friendShip);
    }

    @Override
    public FriendShip remove(User owner, User friend) {
        Optional<FriendShip> friendShip = hasFriendShip(owner.getUsername(), friend.getUsername());
        friendShip.ifPresent(
                friendShip1 -> mongoTemplate.remove(friendShip1).wasAcknowledged()
        );

        return friendShip.orElse(null);

    }

    @Override
    public Optional<FriendShip> hasFriendShip(String owner, String friend) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("left.username").is(owner)
                        .and("right.username").is(friend)
                        .and("state").is(FriendShipState.ACCEPTED)
                        .and("state").ne(FriendShipState.REJECTED),

                Criteria.where("right.username").is(friend)
                        .and("left.username").is(owner)
                        .and("state").is(FriendShipState.ACCEPTED)
                        .and("state").ne(FriendShipState.REJECTED)
        );

        Query query = Query.query(criteria);
        FriendShip friendShip = this.mongoTemplate.findOne(query, FriendShip.class);
        return Optional.ofNullable(friendShip);
    }
}
