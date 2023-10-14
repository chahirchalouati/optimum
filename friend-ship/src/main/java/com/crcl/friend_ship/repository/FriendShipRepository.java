package com.crcl.friend_ship.repository;

import com.crcl.friend_ship.domain.Friendship;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FriendShipRepository extends ReactiveCrudRepository<Friendship, String> {
}
