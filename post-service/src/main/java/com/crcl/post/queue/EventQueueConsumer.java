package com.crcl.post.queue;

import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.queue.ProcessableVideo;
import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.post.synchronizers.ProcessableImageSynchronizer;
import com.crcl.post.synchronizers.ProcessableVideoSynchronizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class EventQueueConsumer {

    private final ProcessableImageSynchronizer synchronizer;
    private final ProcessableVideoSynchronizer videoSynchronizer;

    @RabbitListener(queues = QueueDefinition.PROCESSABLE_IMAGE_QUEUE)
    public void consumeImageUploadEvent(DefaultQEvent<ProcessableImage> event) {
        this.synchronizer.synchronize(event);
    }

    @RabbitListener(queues = QueueDefinition.PROCESSABLE_VIDEO_QUEUE)
    public void consumeVideoUploadEvent(DefaultQEvent<ProcessableVideo> event) {
        this.videoSynchronizer.synchronize(event);
    }
}
