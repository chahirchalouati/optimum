package com.crcl.friend_ship.repository;

import com.crcl.friend_ship.domain.Friendship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface CustomFriendShipRepository {

    Mono<Page<Friendship>> findAll(Pageable pageable);
}
