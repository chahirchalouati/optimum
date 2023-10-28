package com.crcl.friendship.domain;

public enum State {
    ACCEPTED {
        @Override
        public boolean canTransitionTo(State nextState) {
            return nextState == IN_PENDING;
        }
    },
    IN_PENDING {
        @Override
        public boolean canTransitionTo(State nextState) {
            return nextState == ACCEPTED || nextState == REJECTED;
        }
    },
    REJECTED {
        @Override
        public boolean canTransitionTo(State nextState) {
            return nextState == IN_PENDING;
        }
    };

    public abstract boolean canTransitionTo(State nextState);
}
