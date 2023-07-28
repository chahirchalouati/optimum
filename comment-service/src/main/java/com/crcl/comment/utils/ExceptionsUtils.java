package com.crcl.comment.utils;

import com.crcl.core.exceptions.EntityNotFoundException;

import java.util.function.Supplier;

public class ExceptionsUtils {
    public static Supplier<EntityNotFoundException> entityNotFoundException(Long id, String entityName) {
        return () -> new EntityNotFoundException("%s with id %d does not exist".formatted(entityName, id));
    }
}
