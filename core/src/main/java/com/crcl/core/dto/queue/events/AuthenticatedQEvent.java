package com.crcl.core.dto.queue.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.Assert;

import java.util.Map;


@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticatedQEvent<T> extends QEvent<T, AuthenticatedQEvent<T>> {

    private String token;

    public AuthenticatedQEvent<T> withToken(String token) {
        Assert.notNull(token, "The given token can't be null");
        this.token = token;
        return self();
    }

    @Override
    public AuthenticatedQEvent<T> withPayload(T payload) {
        return self().setPayload(payload);
    }

    @Override
    public AuthenticatedQEvent<T> withHeaders(Map<String, Object> headers) {
        return self().setHeaders(headers);
    }

    @Override
    protected AuthenticatedQEvent<T> self() {
        return this;
    }
}
