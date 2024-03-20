package com.crcl.processor.queue;

import com.crcl.core.dto.queue.payloads.CreatePostPayload;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;

public interface EventQueueConsumer {
    void consumeProcessableImageEvent(AuthenticatedQEvent<CreatePostPayload> message);

    void consumeProcessableVideoEvent(AuthenticatedQEvent<CreatePostPayload> message);
}
