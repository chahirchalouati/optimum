package com.crcl.common.dto.requests;

public enum ReactionType {

    ANGRY("\uD83D\uDE20"),
    APPLAUSE("\uD83D\uDC4F"),
    CELEBRATE("\uD83C\uDF89"),
    CONFUSED("\uD83D\uDE15"),
    HAHA("\uD83D\uDE02"),
    LIKE("\uD83D\uDC4D"),
    LOVE("\uD83D\uDC9D"),
    SAD("\uD83D\uDE41"),
    SUPPORT("\uD83D\uDC96"),
    WOW("\uD83D\uDE32");

    ReactionType(String emojis) {
    }

}
