package com.crcl.common.utils;

public interface QueueDefinition {

    // attachments queues
    String UPDATE_IMAGES_QUEUE = "update_attachment_images_queue";
    String STORAGE_RESIZE_IMAGES_QUEUE = "storage_resize_images_queue";

    // audits queues
    String AUDIT_MESSAGE_QUEUE = "audit_message_queue";
    String POST_CREATED_QUEUE = "post_created_queue";

    // audits queues
    String NOTIFY_POST_CREATED_QUEUE = "notify_post_created_queue";
}
