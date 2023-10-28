package com.crcl.friendship.repository;

import com.crcl.friendship.domain.Friendship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface CustomFriendShipRepository {

    Mono<Page<Friendship>> findAll(Pageable pageable);
    Mono<Page<Friendship>> findNonFriend(String username, Pageable pageable);
}
