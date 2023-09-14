package com.crcl.post.queue;

import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.queue.ProcessableVideo;
import com.crcl.core.dto.queue.events.QEvent;

public interface EventQueuePublisher {
    <T> void sendMessage(QEvent<T> QEvent, String queueName);

    void publishProcessableImageEvent(ProcessableImage processableImage);

    void publishProcessableVideoEvent(ProcessableVideo processableVideo);
}
