package com.crcl.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CrclObjectUtils {
    public static <V> void setIfNotNull(V value, Consumer<V> setter) {
        if (Objects.nonNull(value)) {
            setter.accept(value);
        }
    }

    public static <V> void throwIfNull(V value, Supplier<V> setter) {
        if (Objects.isNull(value)) {
            setter.get();
        }
    }

    public static <T> void testAndThrow(T value, Predicate<T> predicate, Class<? extends RuntimeException> exceptionType, String message) {
        if (predicate.test(value)) {
            try {
                throw exceptionType.getConstructor(String.class).newInstance(message);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException("Failed to throw exception of type " + exceptionType.getName(), e);
            }
        }
    }

//    /*
//       Debugging utilities
//     */
//    public static void main(String[] args) {
//        testAndThrow(null, Objects::isNull, IllegalArgumentException.class, "value is null");
//    }
}
