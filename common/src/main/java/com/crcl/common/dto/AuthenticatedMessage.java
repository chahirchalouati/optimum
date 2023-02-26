package com.crcl.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Assert;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticatedMessage<T> extends Message<T> {
    private String token;

    public String getToken() {
        return token;
    }

    public AuthenticatedMessage<T> setToken(String token) {
        Assert.assertNotNull("token can't be null", token);
        this.token = token;
        return this;
    }
}
