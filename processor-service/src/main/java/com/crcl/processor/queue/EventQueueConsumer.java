package com.crcl.processor.queue;

import com.crcl.common.dto.queue.AuthenticatedQEvent;
import com.crcl.common.dto.queue.ImageUpload;

public interface EventQueueConsumer {
    void onReceiveResizeImage(AuthenticatedQEvent<ImageUpload> message);
}
