package com.crcl.post.queue;

import com.crcl.core.dto.queue.ProcessableImageEvent;
import com.crcl.core.dto.queue.ProcessableVideo;

public interface PostQueuePublisher {

    void publishProcessableImageEvent(ProcessableImageEvent processableImageEvent);

    void publishProcessableVideoEvent(ProcessableVideo processableVideo);
}
