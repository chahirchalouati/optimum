package com.crcl.common.dto.queue.events;

import lombok.Data;
import org.springframework.messaging.MessageHeaders;

@Data
public abstract class QEvent<T> {
    protected T payload;
    protected MessageHeaders headers;

    public T getPayload() {
        return payload;
    }

    public QEvent<T> setPayload(T payload) {
        this.payload = payload;
        return this;
    }
}
