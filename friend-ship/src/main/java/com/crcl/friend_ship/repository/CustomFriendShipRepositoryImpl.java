package com.crcl.friend_ship.repository;

import com.crcl.friend_ship.domain.Friendship;
import lombok.RequiredArgsConstructor;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.ResultStatement;
import org.neo4j.cypherdsl.core.Statement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.core.ReactiveNeo4jTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomFriendShipRepositoryImpl implements CustomFriendShipRepository {
    private final ReactiveNeo4jTemplate reactiveNeo4jTemplate;

    @Override
    public Mono<Page<Friendship>> findAll(Pageable pageable) {
        final ResultStatement query = Cypher.match(Cypher.node("f", "Friendship"))
                .returning("f")
                .skip(Cypher.literalOf(pageable.getOffset()))
                .limit(Cypher.literalOf(pageable.getPageSize()))
                .build();

       final Flux<Friendship> friendships = reactiveNeo4jTemplate.findAll(query, Friendship.class);
       final Mono<Long> totalFriendsMono = reactiveNeo4jTemplate.count(Friendship.class);

        return Mono.zip(totalFriendsMono, friendships.collectList())
                .map(tuple -> {
                    Long totalFriends = tuple.getT1();
                    List<Friendship> friendshipList = tuple.getT2();
                    return new PageImpl<>(friendshipList, pageable, totalFriends);
                });
    }
}
