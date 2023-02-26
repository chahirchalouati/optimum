package com.crcl.processor.queue;


import com.crcl.common.queue.ImageUploadEvent;
import com.crcl.processor.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public abstract class ResizeImageQueuePublisher extends MessageQueuePublisher {

    public ResizeImageQueuePublisher(RabbitTemplate rabbitTemplate, UserService userService) {
        super(rabbitTemplate, userService);
    }

    public abstract void publishImageUploadEvent(ImageUploadEvent request);
}
