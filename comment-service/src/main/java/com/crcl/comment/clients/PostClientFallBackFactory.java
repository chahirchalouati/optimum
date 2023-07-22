package com.crcl.comment.clients;

import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Optional;


public class PostClientFallBackFactory implements FallbackFactory<PostClient> {
    @Override
    public PostClient create(Throwable cause) {
        cause.printStackTrace();
        return id -> Optional.empty();
    }
}
