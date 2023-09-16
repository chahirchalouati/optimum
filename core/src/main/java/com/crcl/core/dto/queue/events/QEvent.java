package com.crcl.core.dto.queue.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class QEvent<T, E extends QEvent<T, E>> {

    protected T payload;
    protected Map<String, Object> headers;

    public E setPayload(T payload) {
        Assert.notNull(payload, "The given payload can't be null");
        this.payload = payload;
        return self();
    }

    public E setHeaders(Map<String, Object> headers) {
        this.headers = headers;
        return self();
    }

    public abstract E withPayload(T payload);

    public abstract E withHeaders(Map<String, Object> headers);

    // A method to return a new instance of the same subclass
    protected abstract E self();
}