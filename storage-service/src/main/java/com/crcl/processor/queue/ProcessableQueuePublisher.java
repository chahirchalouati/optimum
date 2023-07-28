package com.crcl.processor.queue;


import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.queue.ProcessableVideo;
import com.crcl.processor.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public abstract class ProcessableQueuePublisher extends EventQueuePublisher {

    public ProcessableQueuePublisher(RabbitTemplate rabbitTemplate, UserService userService) {
        super(rabbitTemplate, userService);
    }

    public abstract void publishProcessableImageEvent(ProcessableImage event);

    public abstract void publishProcessableVideoEvent(ProcessableVideo event);
}
