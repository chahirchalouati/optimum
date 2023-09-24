package com.crcl.post.queue;

import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.queue.ProcessableVideo;
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
    public void publishProcessableImageEvent(ProcessableImage processableImage) {
        var message = new AuthenticatedQEvent<ProcessableImage>();
        message.withToken(userService.getToken())
                .setPayload(processableImage);
        this.publishAuthenticatedMessage(message, QueueDefinition.PROCESSABLE_IMAGE_QUEUE);
        log.debug("Resized image successfully");

    }
    @Override
    public void publishProcessableVideoEvent(ProcessableVideo processableVideo) {
        var message = new AuthenticatedQEvent<ProcessableVideo>();
        message.withToken(userService.getToken())
                .setPayload(processableVideo);
        this.publishAuthenticatedMessage(message, QueueDefinition.PROCESSABLE_VIDEO_QUEUE);
        log.debug("VideoUpload image successfully");

    }
}
