package com.crcl.post.utils;

import java.util.Collection;

public class CrclUtils {
    public static <T extends Collection> void applyIfNotEmpty(T collection, Runnable runnable) {
        if (!collection.isEmpty()) {
            runnable.run();
        }
    }

    public static void applyIf(Boolean shouldApply, Runnable runnable) {
        if (shouldApply) {
            runnable.run();
        }
    }

}
