package com.crcl.friend_ship.service;

import com.crcl.core.utils.generic.ReactiveGenericService;
import com.crcl.friend_ship.domain.Friendship;
import com.crcl.friend_ship.domain.State;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FriendShipService extends ReactiveGenericService<Friendship, String> {
    Mono<Friendship> updateState(String id, State state);


}
