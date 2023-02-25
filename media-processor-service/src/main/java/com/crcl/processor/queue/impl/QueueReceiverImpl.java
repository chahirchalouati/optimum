package com.crcl.processor.queue.impl;

import com.crcl.common.dto.AuthenticatedMessage;
import com.crcl.common.queue.ImageUploadEvent;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.processor.queue.QueueReceiver;
import com.crcl.processor.queue.annotation.SecurityContextInterceptor;
import com.crcl.processor.service.ImageProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class QueueReceiverImpl implements QueueReceiver {

    private final ImageProcessor imageProcessor;

    @SecurityContextInterceptor
    @RabbitListener(queues = QueueDefinition.STORAGE_RESIZE_IMAGES_QUEUE)
    public void onReceiveResizeImage(Message<AuthenticatedMessage<ImageUploadEvent>> message) {
        final ImageUploadEvent event = message.getPayload().getPayload();
        imageProcessor.process(event);
    }
}
