package com.crcl.friendship.repository;

import com.crcl.friendship.domain.Friendship;
import lombok.RequiredArgsConstructor;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.ResultStatement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.core.ReactiveNeo4jOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomFriendShipRepositoryImpl implements CustomFriendShipRepository {

    private final ReactiveNeo4jOperations reactiveNeo4jOperations;

    @Override
    public Mono<Page<Friendship>> findAll(Pageable pageable) {

        final ResultStatement query = Cypher.match(Cypher.node("friendship").named("f"))
                .returning("f")
                .skip(Cypher.literalOf(pageable.getOffset()))
                .limit(Cypher.literalOf(pageable.getPageSize()))
                .build();

        final Flux<Friendship> friendships = reactiveNeo4jOperations.findAll(query, Friendship.class);
        final Mono<Long> totalFriendsMono = reactiveNeo4jOperations.count(Friendship.class);

        return Mono.zip(totalFriendsMono, friendships.collectList())
                .map(tuple -> {
                    Long totalFriends = tuple.getT1();
                    List<Friendship> friendshipList = tuple.getT2();
                    return new PageImpl<>(friendshipList, pageable, totalFriends);
                });
    }

    @Override
    public Mono<Page<Friendship>> findNonFriend(String username, Pageable pageable) {
        // TODO: 22/10/23 update the query to retrieve users correctly
        final ResultStatement query = Cypher.match(Cypher.node("friendship").named("f"))
                .returning("f")
                .skip(Cypher.literalOf(pageable.getOffset()))
                .limit(Cypher.literalOf(pageable.getPageSize()))
                .build();

        final Flux<Friendship> friendships = reactiveNeo4jOperations.findAll(query, Friendship.class);
        final Mono<Long> totalFriendsMono = reactiveNeo4jOperations.count(Friendship.class);

        return Mono.zip(totalFriendsMono, friendships.collectList())
                .map(tuple -> {
                    Long totalFriends = tuple.getT1();
                    List<Friendship> friendshipList = tuple.getT2();
                    return new PageImpl<>(friendshipList, pageable, totalFriends);
                });
    }
}
