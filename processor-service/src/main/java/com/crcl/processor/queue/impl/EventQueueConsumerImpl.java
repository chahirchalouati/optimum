package com.crcl.processor.queue.impl;

import com.crcl.common.annotation.SecurityContextInterceptor;
import com.crcl.common.dto.queue.AuthenticatedQEvent;
import com.crcl.common.dto.queue.ImageUpload;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.processor.queue.EventQueueConsumer;
import com.crcl.processor.service.ImageProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventQueueConsumerImpl implements EventQueueConsumer {
    private final ImageProcessor imageProcessor;

    @SecurityContextInterceptor
    @RabbitListener(queues = QueueDefinition.STORAGE_RESIZE_IMAGES_QUEUE)
    public void onReceiveResizeImage(Message<AuthenticatedQEvent<ImageUpload>> message) {
        imageProcessor.process(message.getPayload());
    }
}



