package com.crcl.friend_ship.controller;

import com.crcl.friend_ship.domain.State;
import com.crcl.friend_ship.service.FriendShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crcl.friend_ship.domain.Friendship;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("friend-ships")
@RequiredArgsConstructor
public class FriendShipController {

    private final FriendShipService friendShipService;

    @PostMapping
    public Mono<Friendship> createFriendship(@RequestBody Friendship friendship) {
        return friendShipService.save(friendship);
    }

    @GetMapping
    public Flux<Friendship> getAllFriendships() {
        return friendShipService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Friendship> getFriendshipById(@PathVariable String id) {
        return friendShipService.findById(id);
    }

    @PutMapping("/{id}/accept")
    public Mono<Friendship> acceptFriendship(@PathVariable String id) {
        return friendShipService.updateState(id, State.ACCEPTED);
    }

    @PutMapping("/{id}/reject")
    public Mono<Friendship> rejectFriendship(@PathVariable String id) {
        return friendShipService.updateState(id, State.REJECTED);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteFriendship(@PathVariable String id) {
        return friendShipService.deleteById(id);
    }
}
