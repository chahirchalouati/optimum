package com.crcl.post.queue;

import com.crcl.core.dto.queue.ProcessableImageEvent;
import com.crcl.core.dto.queue.ProcessableVideoEvent;

public interface PostQueuePublisher {

    void publishProcessableImageEvent(ProcessableImageEvent processableImageEvent);

    void publishProcessableVideoEvent(ProcessableVideoEvent processableVideoEvent);
}
