package com.crcl.friendship.repository;

import com.crcl.friendship.domain.Friendship;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.neo4j.cypherdsl.core.Condition;
import org.neo4j.cypherdsl.core.Cypher;
import org.neo4j.cypherdsl.core.ResultStatement;
import org.neo4j.cypherdsl.core.SortItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.core.ReactiveNeo4jOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

// TODO: 30/10/23 create a custom filter builder

@Repository
@RequiredArgsConstructor
public class CustomFriendShipRepositoryImpl implements CustomFriendShipRepository {

    private final ReactiveNeo4jOperations reactiveNeo4jOperations;

    @Override
    public Mono<Page<Friendship>> findAll(Pageable pageable) {
        final SortItem sortItem = Cypher.sort(Cypher.property(Friendship.ALIAS, Friendship.Fields.createdAt)).descending();

        final ResultStatement query = Cypher.match(Cypher.node(Friendship.NODE).named(Friendship.ALIAS))
                .returning(Friendship.ALIAS)
                .orderBy(sortItem)
                .skip(Cypher.literalOf(pageable.getOffset()))
                .limit(Cypher.literalOf(pageable.getPageSize()))
                .build();

        return getPage(pageable, query);
    }

    @Override
    public Mono<Page<Friendship>> findNonFriend(String username, Pageable pageable) {
        final Condition notSenderCondition = Cypher.property(Friendship.ALIAS, Friendship.Fields.sender).isNotEqualTo(Cypher.literalOf(username));
        final Condition notReceiverCondition = Cypher.property(Friendship.ALIAS, Friendship.Fields.receiver).isNotEqualTo(Cypher.literalOf(username));
        final Condition combinedCondition = notSenderCondition.and(notReceiverCondition);
        final SortItem sortItem = Cypher.sort(Cypher.property(Friendship.ALIAS, Friendship.Fields.createdAt)).descending();

        final ResultStatement query = Cypher.match(Cypher.node(Friendship.NODE).named(Friendship.ALIAS))
                .where(combinedCondition)
                .returning(Friendship.ALIAS)
                .orderBy(sortItem)
                .skip(Cypher.literalOf(pageable.getOffset()))
                .limit(Cypher.literalOf(pageable.getPageSize()))
                .build();

        return getPage(pageable, query);
    }

    @NotNull
    private Mono<Page<Friendship>> getPage(Pageable pageable, ResultStatement query) {
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
