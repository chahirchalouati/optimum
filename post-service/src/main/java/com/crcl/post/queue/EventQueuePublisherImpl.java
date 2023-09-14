package com.crcl.post.queue;

import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.queue.ProcessableVideo;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import com.crcl.core.dto.queue.events.QEvent;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.post.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class EventQueuePublisherImpl implements EventQueuePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;

    @Override
    public <T> void sendMessage(QEvent<T> QEvent, String queueName) {
        rabbitTemplate.convertAndSend(queueName, QEvent);
    }

    @Override
    public void publishProcessableImageEvent(ProcessableImage processableImage) {
        var message = new AuthenticatedQEvent<>();
        message.setToken(userService.getToken())
                .setPayload(processableImage);
        this.sendMessage(message, QueueDefinition.PROCESSABLE_IMAGE_QUEUE);
        log.debug("Resized image successfully");

    }

    @Override
    public void publishProcessableVideoEvent(ProcessableVideo processableVideo) {
        var message = new AuthenticatedQEvent<>();
        message.setToken(userService.getToken())
                .setPayload(processableVideo);
        this.sendMessage(message, QueueDefinition.PROCESSABLE_VIDEO_QUEUE);
        log.debug("VideoUpload image successfully");

    }
}
