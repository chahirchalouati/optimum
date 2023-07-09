package com.crcl.processor.queue;

import com.crcl.common.dto.queue.ProcessableImage;
import com.crcl.common.dto.queue.ProcessableVideo;
import com.crcl.common.dto.queue.events.AuthenticatedQEvent;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.processor.configuration.filters.JwtFilterInterceptor;
import com.crcl.processor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProcessableQueuePublisherImpl extends ProcessableQueuePublisher {
    private final JwtFilterInterceptor jwtFilterInterceptor;

    public ProcessableQueuePublisherImpl(RabbitTemplate rabbitTemplate,
                                         UserService userService,
                                         JwtFilterInterceptor jwtFilterInterceptor) {
        super(rabbitTemplate, userService);
        this.jwtFilterInterceptor = jwtFilterInterceptor;
    }

    @Override
    public void publishProcessableImageEvent(ProcessableImage event) {
        final boolean present = jwtFilterInterceptor.getJwt().isPresent();
        if (present) {
            var jwt = jwtFilterInterceptor.getJwt().get();
            var message = new AuthenticatedQEvent<>();
            message.setToken(jwt.getTokenValue())
                    .setPayload(event);
            this.publishAuthenticatedMessage(message, QueueDefinition.PROCESSABLE_IMAGE_QUEUE);
            log.debug("Resized image successfully");
            return;
        }
        log.debug("Unable to resize image due to missing JWT token");
    }

    @Override
    public void publishProcessableVideoEvent(ProcessableVideo event) {
        final boolean present = jwtFilterInterceptor.getJwt().isPresent();
        if (present) {
            var jwt = jwtFilterInterceptor.getJwt().get();
            var message = new AuthenticatedQEvent<>();
            message.setToken(jwt.getTokenValue())
                    .setPayload(event);
            this.publishAuthenticatedMessage(message, QueueDefinition.PROCESSABLE_VIDEO_QUEUE);
            log.debug("VideoUpload image successfully");
            return;
        }
        log.debug("Unable to VideoUpload to missing JWT token");
    }

}
