package com.crcl.post.queue;

import com.crcl.core.dto.queue.ProcessableImageEvent;
import com.crcl.core.dto.queue.ProcessableVideoEvent;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import com.crcl.core.queue.QueuePublisher;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.post.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventQueuePublisher extends QueuePublisher implements PostQueuePublisher {

    private final UserService userService;

    public EventQueuePublisher(RabbitTemplate rabbitTemplate, UserService userService) {
        super(rabbitTemplate);
        this.userService = userService;
    }

    @Override
    public void publishProcessableImageEvent(ProcessableImageEvent processableImageEvent) {
        var message = new AuthenticatedQEvent<ProcessableImageEvent>();
        message.withToken(userService.getToken())
                .setPayload(processableImageEvent);
        this.publishAuthenticatedMessage(message, QueueDefinition.PROCESSABLE_IMAGE_QUEUE);
        log.debug("Resized image successfully");

    }

    @Override
    public void publishProcessableVideoEvent(ProcessableVideoEvent processableVideoEvent) {
        var message = new AuthenticatedQEvent<ProcessableVideoEvent>();
        message.withToken(userService.getToken())
                .setPayload(processableVideoEvent);
        this.publishAuthenticatedMessage(message, QueueDefinition.PROCESSABLE_VIDEO_QUEUE);
        log.debug("VideoUpload image successfully");

    }
}
