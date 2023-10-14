package com.crcl.friend_ship.domain;

import com.crcl.core.dto.UserDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Document
public class Friendship {

    @Id
    private String id;
    private UserDto user;
    private UserDto friend;
    private State state = State.IN_PENDING;
    private LocalDateTime createdAt;
    private UserDto createdBy;
    private LocalDateTime deletedAt;
    private UserDto deletedBy;
    private LocalDateTime acceptedAt;
    private LocalDateTime rejectedAt;

    public void transitionToState(State nextState) {
        if (!state.canTransitionTo(nextState)) {
            throw new IllegalStateException("Invalid state transition from " + state + " to " + nextState);
        }
        state = nextState;
    }
}

