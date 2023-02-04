package com.crcl.comment.exceptions.utils;

import com.crcl.common.exceptions.EntityNotFoundException;

import java.util.function.Supplier;

public class CommentExceptionUtils {

    public static Supplier<EntityNotFoundException> commentNotFoundException(Long id) {
        return () -> new EntityNotFoundException("comment with id %d does not exists".formatted(id));
    }
}
