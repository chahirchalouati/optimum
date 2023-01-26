package com.crcl.authentication.repository;

import com.crcl.authentication.domain.FriendShip;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FriendShipRepository extends MongoRepository<FriendShip, String>, CustomFriendShipRepository {
}
