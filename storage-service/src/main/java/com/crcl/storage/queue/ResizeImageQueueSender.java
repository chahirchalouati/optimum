package com.crcl.storage.queue;


import com.crcl.storage.dto.ResizeImageRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public abstract class ResizeImageQueueSender extends MessageQueueSender {

    public ResizeImageQueueSender(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    public abstract void resizeImage(ResizeImageRequest request);
}
