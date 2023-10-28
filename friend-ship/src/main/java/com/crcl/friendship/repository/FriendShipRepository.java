package com.crcl.friendship.repository;

import com.crcl.friendship.domain.Friendship;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendShipRepository extends ReactiveNeo4jRepository<Friendship, String>, CustomFriendShipRepository {
}
