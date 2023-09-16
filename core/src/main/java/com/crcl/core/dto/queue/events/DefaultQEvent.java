package com.crcl.core.dto.queue.events;

import org.springframework.messaging.MessageHeaders;

public class DefaultQEvent<T> extends QEvent<T, DefaultQEvent<T>> {

    @Override
    public DefaultQEvent<T> withPayload(T payload) {
        return self().setPayload(payload);
    }

    @Override
    public DefaultQEvent<T> withHeaders(MessageHeaders headers) {
        return self().setHeaders(headers);
    }

    @Override
    protected DefaultQEvent<T> self() {
        return this;
    }
}
