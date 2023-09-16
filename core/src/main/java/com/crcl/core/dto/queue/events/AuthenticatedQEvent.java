package com.crcl.core.dto.queue.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.Assert;

@Getter
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticatedQEvent<T> extends QEvent<T, AuthenticatedQEvent<T>> {

    private String token;

    public AuthenticatedQEvent<T> setToken(String token) {
        Assert.notNull(token, "The given token can't be null");
        this.token = token;
        return self();
    }

    @Override
    public AuthenticatedQEvent<T> withPayload(T payload) {
        return self().setPayload(payload);
    }

    @Override
    public AuthenticatedQEvent<T> withHeaders(MessageHeaders headers) {
        return self().setHeaders(headers);
    }

    @Override
    protected AuthenticatedQEvent<T> self() {
        return this;
    }
}
