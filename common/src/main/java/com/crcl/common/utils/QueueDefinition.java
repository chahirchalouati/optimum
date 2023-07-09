package com.crcl.common.utils;

public interface QueueDefinition {

    // attachments queues
    String UPDATE_IMAGES_QUEUE = "update_attachment_images_queue";
    String PROCESSABLE_VIDEO_QUEUE = "processable_video_queue";
    String PROCESSABLE_IMAGE_QUEUE = "processable_image_queue";

    // audits queues
    String AUDIT_MESSAGE_QUEUE = "audit_message_queue";
    String POST_CREATED_QUEUE = "post_created_queue";

    // audits queues
    String NOTIFY_POST_CREATED_QUEUE = "notify_post_created_queue";

    // generic
    String NOTIFY_ASYNC_QUEUE = "notify_async_queue";
}
