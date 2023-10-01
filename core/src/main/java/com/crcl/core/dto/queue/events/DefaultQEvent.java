package com.crcl.core.dto.queue.events;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class DefaultQEvent<T> extends QEvent<T, DefaultQEvent<T>> {
    public DefaultQEvent(T payload) {
        super(payload);
    }

    @Override
    public DefaultQEvent<T> withPayload(T payload) {
        return self().setPayload(payload);
    }

    @Override
    public DefaultQEvent<T> withHeaders(Map<String, Object> headers) {
        return self().setHeaders(headers);
    }

    @Override
    protected DefaultQEvent<T> self() {
        return this;
    }
}
