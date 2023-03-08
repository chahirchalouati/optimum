package com.crcl.processor.queue;

import com.crcl.common.dto.AuthenticatedQEvent;
import com.crcl.common.queue.ImageUpload;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.processor.configuration.filters.JwtFilterInterceptor;
import com.crcl.processor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResizeImageQueuePublisherImpl extends ResizeImageQueuePublisher {
    private final JwtFilterInterceptor jwtFilterInterceptor;

    public ResizeImageQueuePublisherImpl(RabbitTemplate rabbitTemplate,
                                         UserService userService,
                                         JwtFilterInterceptor jwtFilterInterceptor) {
        super(rabbitTemplate, userService);
        this.jwtFilterInterceptor = jwtFilterInterceptor;
    }

    @Override
    public void publishImageUploadEvent(ImageUpload event) {
        final boolean present = jwtFilterInterceptor.getJwt().isPresent();
        if (present) {
            final var jwt = jwtFilterInterceptor.getJwt().get();
            final var message = new AuthenticatedQEvent<>();
            message.setToken(jwt.getTokenValue())
                    .setPayload(event);
            this.publishAuthenticatedMessage(message, QueueDefinition.STORAGE_RESIZE_IMAGES_QUEUE);
            log.debug("Resized image successfully");
            return;
        }
        log.debug("Unable to resize image due to missing JWT token");
    }

}
