package com.crcl.friend_ship.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Node("friendship")
public class Friendship {

    @Id
    @GeneratedValue
    private String id;
    private String sender;
    private String receiver;
    private State state = State.IN_PENDING;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime acceptedAt;
    private LocalDateTime rejectedAt;

    public void transitionToState(State nextState) {
        if (!state.canTransitionTo(nextState)) {
            throw new IllegalStateException("Invalid state transition from " + state + " to " + nextState);
        }
        state = nextState;
    }
}

