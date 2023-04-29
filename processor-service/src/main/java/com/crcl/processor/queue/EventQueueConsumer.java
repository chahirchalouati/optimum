package com.crcl.processor.queue;

import com.crcl.common.dto.queue.AuthenticatedQEvent;
import com.crcl.common.dto.queue.ImageUpload;
import org.springframework.messaging.Message;

public interface EventQueueConsumer {
    void onReceiveResizeImage(Message<AuthenticatedQEvent<ImageUpload>> message);
}
