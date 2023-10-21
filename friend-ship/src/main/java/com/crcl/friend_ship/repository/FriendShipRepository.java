package com.crcl.friend_ship.repository;

import com.crcl.friend_ship.domain.Friendship;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendShipRepository extends ReactiveNeo4jRepository<Friendship, String>, CustomFriendShipRepository {
}
