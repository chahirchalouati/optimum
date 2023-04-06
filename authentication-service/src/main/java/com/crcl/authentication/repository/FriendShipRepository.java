package com.crcl.authentication.repository;

import com.crcl.authentication.domain.FriendShip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FriendShipRepository extends MongoRepository<FriendShip, String>, CustomFriendShipRepository {
    @Query("""
            {$and:[
            {state: 'ACCEPTED'},
            {$or: [ 
            { 'sender.username': ?0 },
            { 'recipient.username': ?0 }]
            }]}
            """)
    Page<FriendShip> findFriends(String username, Pageable pageable);
}
