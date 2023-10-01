package com.crcl.core.utils;

public interface QueueDefinition {

    // attachments queues
    String PROCESSABLE_VIDEO_QUEUE = "processable.video";
    String PROCESSABLE_IMAGE_QUEUE = "processable.image";
    String PUSH_PROCESSED_IMAGE_QUEUE = "push.processed.image";
    String PUSH_PROCESSED_VIDEO_QUEUE = "push.processed.video";

    // audits queues
    String AUDIT_MESSAGE_QUEUE = "audit.message";

    // posts queues
    String POST_CREATED_QUEUE = "post.created";

    // notify queues
    String NOTIFY_POST_CREATED_QUEUE = "notify.post.created";

    // generic
    String NOTIFY_ASYNC_QUEUE = "notify.async";
}
