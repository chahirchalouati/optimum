package com.crcl.post.utils;

import java.util.Collection;
import java.util.Objects;

public class CrclUtils {
    /**
     * run the given code if the given collection is not empty
     *
     * @param collection
     * @param runnable
     * @param <T>        generic value that extends collection interface
     */
    public static <T extends Collection> void applyIfNotEmpty(T collection, Runnable runnable) {
        if (Objects.nonNull(collection) && !collection.isEmpty()) {
            runnable.run();
        }
    }

    public static <T> void applyIf(Boolean shouldApply, Runnable runnable) {
        if (shouldApply)
            runnable.run();
    }

}
