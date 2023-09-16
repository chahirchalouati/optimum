package com.crcl.core.dto.queue.events;

import java.util.Map;

public class DefaultQEvent<T> extends QEvent<T, DefaultQEvent<T>> {

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
