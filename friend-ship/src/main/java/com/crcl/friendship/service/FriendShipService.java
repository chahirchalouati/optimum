package com.crcl.friendship.service;

import com.crcl.core.utils.generic.ReactiveGenericService;
import com.crcl.friendship.domain.Friendship;
import com.crcl.friendship.domain.State;
import reactor.core.publisher.Mono;

public interface FriendShipService extends ReactiveGenericService<Friendship, String> {
    Mono<Friendship> updateState(String id, State state);


}
