package com.crcl.post.queue;

import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.queue.ProcessableVideo;

public interface PostQueuePublisher {

    void publishProcessableImageEvent(ProcessableImage processableImage);

    void publishProcessableVideoEvent(ProcessableVideo processableVideo);
}
