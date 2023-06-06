package com.crcl.processor.queue;

import com.crcl.common.dto.queue.ImageUpload;
import com.crcl.common.dto.queue.events.AuthenticatedQEvent;

public interface EventQueueConsumer {
    void onReceiveResizeImage(AuthenticatedQEvent<ImageUpload> message);
}
