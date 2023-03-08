package com.crcl.common.utils;

public interface QueueDefinition {

    // attachments queues
    String UPDATE_ATTACHMENT_IMAGES_QUEUE = "update_attachment_images_queue";
    String STORAGE_RESIZE_IMAGES_QUEUE = "storage_resize_images_queue";

    // audits queues
    String AUDIT_MESSAGE_QUEUE = "audit_message_queue";
}
