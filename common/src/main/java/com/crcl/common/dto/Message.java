package com.crcl.common.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Message<T> {
    protected T payload;
    protected Map<String, Object> headers = new HashMap<>();

    public T getPayload() {
        return payload;
    }

    public Message<T> setPayload(T payload) {
        this.payload = payload;
        return this;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public Message<T> setHeaders(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }
}
