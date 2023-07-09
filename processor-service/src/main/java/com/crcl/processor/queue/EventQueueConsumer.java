package com.crcl.processor.queue;

import com.crcl.common.dto.queue.ProcessableImage;
import com.crcl.common.dto.queue.ProcessableVideo;
import com.crcl.common.dto.queue.events.AuthenticatedQEvent;

public interface EventQueueConsumer {
    void consumeProcessableImageEvent(AuthenticatedQEvent<ProcessableImage> message);

    void consumeProcessableVideoEvent(AuthenticatedQEvent<ProcessableVideo> message);
}
