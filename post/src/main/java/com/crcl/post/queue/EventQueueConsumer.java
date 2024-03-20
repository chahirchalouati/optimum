package com.crcl.post.queue;

import com.crcl.core.dto.queue.ProcessableImageEvent;
import com.crcl.core.dto.queue.ProcessableVideoEvent;
import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.post.synchronizers.ImagesSynchronizer;
import com.crcl.post.synchronizers.VideoSynchronizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class EventQueueConsumer {

    private final ImagesSynchronizer synchronizer;
    private final VideoSynchronizer videoSynchronizer;

    @RabbitListener(queues = QueueDefinition.PUSH_PROCESSED_IMAGE_QUEUE)
    public void consumeImageUploadEvent(DefaultQEvent<ProcessableImageEvent> event) {
        this.synchronizer.synchronize(event);
    }

    @RabbitListener(queues = QueueDefinition.PUSH_PROCESSED_VIDEO_QUEUE)
    public void consumeVideoUploadEvent(DefaultQEvent<ProcessableVideoEvent> event) {
        this.videoSynchronizer.synchronize(event);
    }
}
