package com.crcl.friendship.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Node(Friendship.NODE)
@FieldNameConstants
public class Friendship {
    public static final String NODE = "friendship";
    public static final String ALIAS = "f";

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

