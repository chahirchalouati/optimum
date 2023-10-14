package com.crcl.friend_ship.service.impl;

import com.crcl.friend_ship.domain.Friendship;
import com.crcl.friend_ship.domain.State;
import com.crcl.friend_ship.repository.FriendShipRepository;
import com.crcl.friend_ship.service.FriendShipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendShipServiceImpl implements FriendShipService {

    private final FriendShipRepository friendShipRepository;

    @Override
    public Mono<Friendship> save(Friendship friendship) {
        friendship.setCreatedAt(LocalDateTime.now());
        return friendShipRepository.save(friendship)
                .doOnSuccess(savedFriendship -> log.info("Saved friendship: {}", savedFriendship));
    }

    @Override
    public Flux<Friendship> saveAll(List<Friendship> friendships) {
        LocalDateTime now = LocalDateTime.now();
        for (Friendship friendship : friendships) {
            friendship.setCreatedAt(now);
        }

        return friendShipRepository.saveAll(friendships)
                .doOnNext(savedFriendship -> log.info("Saved friendship: {}", savedFriendship));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return friendShipRepository.deleteById(id)
                .doOnTerminate(() -> log.info("Deleted friendship with ID: {}", id));
    }

    @Override
    public Mono<Friendship> findById(String id) {
        return friendShipRepository.findById(id)
                .doOnSuccess(friendship -> {
                    if (friendship != null) {
                        log.info("Found friendship by ID {}: {}", id, friendship);
                    } else {
                        log.info("Friendship with ID {} not found", id);
                    }
                });
    }

    @Override
    public Flux<Friendship> findAll() {
        return friendShipRepository.findAll()
                .doOnNext(friendship -> log.info("Found friendship: {}", friendship));
    }

    @Override
    public Mono<Page<Friendship>> findAll(Pageable pageable) {
        return Mono.empty();
    }

    @Override
    public Mono<Friendship> update(Friendship updatedFriendship, String id) {
        return friendShipRepository.findById(id)
                .flatMap(existingFriendship -> {
                    existingFriendship.setState(updatedFriendship.getState());
                    existingFriendship.setAcceptedAt(LocalDateTime.now());
                    return friendShipRepository.save(existingFriendship);
                })
                .doOnSuccess(savedFriendship -> log.info("Updated friendship: {}", savedFriendship));
    }

    @Override
    public Mono<Friendship> updateState(String id, State newState) {
        return friendShipRepository.findById(id)
                .flatMap(existingFriendship -> {
                    try {
                        existingFriendship.transitionToState(newState);
                        if (newState == State.ACCEPTED) {
                            existingFriendship.setAcceptedAt(LocalDateTime.now());
                        } else if (newState == State.REJECTED) {
                            existingFriendship.setRejectedAt(LocalDateTime.now());
                        }
                        return friendShipRepository.save(existingFriendship);
                    } catch (IllegalStateException e) {
                        return Mono.error(new RuntimeException("Invalid state transition: " + e.getMessage()));
                    }
                });
    }
}
