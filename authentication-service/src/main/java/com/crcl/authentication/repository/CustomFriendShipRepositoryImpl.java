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
    public FriendShip link(User sender, User recipient, FriendShipState state) {
        var friendShip = new FriendShip();
        friendShip.setSender(sender);
        friendShip.setRecipient(recipient);
        friendShip.setState(state);
        return mongoTemplate.save(friendShip);
    }

    @Override
    public FriendShip remove(User sender, User recipient) {
        return areFriends(sender.getUsername(), recipient.getUsername())
                .map(friendShip -> {
                    mongoTemplate.remove(friendShip).wasAcknowledged();
                    return friendShip;
                })
                .orElse(null);
    }

    @Override
    public Optional<FriendShip> areFriends(String sender, String recipient) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("sender.username").is(recipient)
                        .and("recipient.username").is(sender),

                Criteria.where("recipient.username").is(sender)
                        .and("sender.username").is(recipient)
        );

        Query query = Query.query(criteria);
        FriendShip friendShip = this.mongoTemplate.findOne(query, FriendShip.class);
        return Optional.ofNullable(friendShip);
    }
}
