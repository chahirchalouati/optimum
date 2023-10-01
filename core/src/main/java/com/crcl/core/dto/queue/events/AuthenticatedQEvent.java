package com.crcl.core.dto.queue.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.Assert;

import java.util.Map;


@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticatedQEvent<T> extends QEvent<T, AuthenticatedQEvent<T>> {

    private String token;
    private Object securityContext;

    public AuthenticatedQEvent<T> withToken(String token) {
        Assert.notNull(token, "The given token can't be null");
        this.token = token;
        return self();
    }

    @Deprecated(forRemoval = true, since = "1.0.2")
    public AuthenticatedQEvent<T> withSecurityContext(Object securityContext) {
        Assert.notNull(securityContext, "The given securityContext can't be null");
        this.securityContext = securityContext;
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

    public DefaultQEvent<T> asDefaultQEvent() {
        return new DefaultQEvent<T>().withPayload(this.payload).withHeaders(this.headers);
    }

    @Override
    protected AuthenticatedQEvent<T> self() {
        return this;
    }
}
