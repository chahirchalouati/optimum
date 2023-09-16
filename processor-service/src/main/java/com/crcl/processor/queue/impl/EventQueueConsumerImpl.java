package com.crcl.processor.queue.impl;

import com.crcl.core.annotation.SecurityContextInterceptor;
import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.queue.ProcessableVideo;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.processor.queue.EventQueueConsumer;
import com.crcl.processor.service.ImageProcessor;
import com.crcl.processor.service.VideoProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventQueueConsumerImpl implements EventQueueConsumer {
    private final ImageProcessor imageProcessor;
    private final VideoProcessor videoProcessor;

    @SecurityContextInterceptor
    @RabbitListener(queues = QueueDefinition.PROCESSABLE_IMAGE_QUEUE)
    public void consumeProcessableImageEvent(AuthenticatedQEvent<ProcessableImage> message) {
        imageProcessor.process(message);
    }

    @SecurityContextInterceptor
    @RabbitListener(queues = QueueDefinition.PROCESSABLE_VIDEO_QUEUE)
    public void consumeProcessableVideoEvent(AuthenticatedQEvent<ProcessableVideo> message) {
        videoProcessor.process(message);
    }
}



