package com.crcl.processor.queue;

import com.crcl.common.dto.AuthenticatedMessage;
import com.crcl.common.dto.DefaultMessage;
import com.crcl.common.queue.ImageUploadEvent;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.processor.configuration.filters.JwtFilterInterceptor;
import com.crcl.processor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResizeImageQueueSenderImpl extends ResizeImageQueueSender {
    private final JwtFilterInterceptor jwtFilterInterceptor;

    public ResizeImageQueueSenderImpl(RabbitTemplate rabbitTemplate,
                                      UserService userService,
                                      JwtFilterInterceptor jwtFilterInterceptor) {
        super(rabbitTemplate, userService);
        this.jwtFilterInterceptor = jwtFilterInterceptor;
    }

    @Override
    public void resizeImage(ImageUploadEvent request) {
        final boolean present = jwtFilterInterceptor.getJwt().isPresent();
        if (present) {
            final var jwt = jwtFilterInterceptor.getJwt().get();
            final var message = new AuthenticatedMessage<>();
            message.setToken(jwt.getTokenValue())
                    .setPayload(request);
            this.sendAuthenticatedMessage(message, QueueDefinition.STORAGE_RESIZE_IMAGES_QUEUE);
            log.debug("Resized image successfully");
            return;
        }
        log.debug("Unable to resize image due to missing JWT token");
    }

    @Override
    public void updateImageAttachment(ImageUploadEvent fileUploadResponse) {
        final var message = new DefaultMessage<ImageUploadEvent>()
                .setPayload(fileUploadResponse);
        this.sendMessage(message, QueueDefinition.UPDATE_ATTACHMENT_IMAGES_QUEUE);
    }

}
