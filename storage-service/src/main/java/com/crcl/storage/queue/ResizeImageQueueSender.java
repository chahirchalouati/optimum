package com.crcl.storage.queue;


import com.crcl.storage.dto.ResizeImageRequest;
import com.crcl.storage.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public abstract class ResizeImageQueueSender extends MessageQueueSender {

    public ResizeImageQueueSender(RabbitTemplate rabbitTemplate, UserService userService) {
        super(rabbitTemplate, userService);
    }

    public abstract void resizeImage(ResizeImageRequest request);
}
