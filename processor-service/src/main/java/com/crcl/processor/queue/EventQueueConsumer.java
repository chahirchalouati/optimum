package com.crcl.processor.queue;

import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.queue.ProcessableVideo;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;

public interface EventQueueConsumer {
    void consumeProcessableImageEvent(AuthenticatedQEvent<ProcessableImage> message);

    void consumeProcessableVideoEvent(AuthenticatedQEvent<ProcessableVideo> message);
}
