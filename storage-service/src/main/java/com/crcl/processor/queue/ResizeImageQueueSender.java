package com.crcl.processor.queue;


import com.crcl.common.queue.ImageUploadEvent;
import com.crcl.processor.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public abstract class ResizeImageQueueSender extends MessageQueueSender {

    public ResizeImageQueueSender(RabbitTemplate rabbitTemplate, UserService userService) {
        super(rabbitTemplate, userService);
    }

    public abstract void resizeImage(ImageUploadEvent request);

    public abstract void updateImageAttachment(ImageUploadEvent imageUploadEvent);
}
