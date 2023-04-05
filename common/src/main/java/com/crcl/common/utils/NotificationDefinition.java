package com.crcl.common.utils;

public enum NotificationDefinition {

    NOTIFY_POST_CREATED("notify_post_created"),
    NOTIFY_POST_LIKE("notify_post_like"),
    NOTIFY_POST_SHARED("notify_post_shared"),
    NOTIFY_POST_TAG("notify_post_tag"),

    NOTIFY_COMMENT_CREATED("notify_comment_created"),
    NOTIFY_COMMENT_LIKE("notify_comment_like"),
    NOTIFY_COMMENT_TAG("notify_comment_tag");

    NotificationDefinition(String type) {
    }
}
