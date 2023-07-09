package com.crcl.post.utils;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

public class CrclUtils {

    public static <T extends Collection> void applyIfNotEmpty(T collection, Runnable runnable) {
        if (Objects.nonNull(collection) && !collection.isEmpty()) {
            runnable.run();
        }
    }    public static <T extends Collection> void applyIfNotEmpty(T collection, Consumer<T> consumable) {
        if (Objects.nonNull(collection) && !collection.isEmpty()) {
            consumable.accept(collection);
        }
    }

    public static <T> void applyIf(boolean shouldApply, Runnable runnable) {
        if (shouldApply)
            runnable.run();
    }

    public static <T> void applyIfNotNull(T object, Consumer<T> consumable) {
        if (Objects.nonNull(object))
            consumable.accept(object);
    }

}
