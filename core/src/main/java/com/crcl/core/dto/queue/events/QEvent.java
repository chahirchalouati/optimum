package com.crcl.core.dto.queue.events;

import lombok.Data;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.Assert;

@Data
public abstract class QEvent<T, E extends QEvent<T, E>> {
    protected T payload;
    protected MessageHeaders headers;

    public E setPayload(T payload) {
        Assert.notNull(payload, "The given payload can't be null");
        this.payload = payload;
        return self();
    }

    public E setHeaders(MessageHeaders headers) {
        Assert.notNull(headers, "The given payload can't be null");
        this.headers = headers;
        return self();
    }

    public abstract E withPayload(T payload);

    public abstract E withHeaders(MessageHeaders headers);

    // A method to return a new instance of the same subclass
    protected abstract E self();
}