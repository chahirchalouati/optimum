package com.crcl.common.dto.queue;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Assert;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticatedQEvent<T> extends QEvent<T> {
    private String token;

    public String getToken() {
        return token;
    }

    public AuthenticatedQEvent<T> setToken(String token) {
        Assert.assertNotNull("token can't be null", token);
        this.token = token;
        return this;
    }
}
