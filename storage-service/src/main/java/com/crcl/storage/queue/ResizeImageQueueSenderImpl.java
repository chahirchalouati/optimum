package com.crcl.storage.queue;

import com.crcl.common.dto.AuthenticatedMessage;
import com.crcl.common.dto.DefaultMessage;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.storage.configuration.filters.JwtFilterInterceptor;
import com.crcl.storage.dto.FileUploadResponse;
import com.crcl.storage.dto.ResizeImageRequest;
import com.crcl.storage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
public class ResizeImageQueueSenderImpl extends ResizeImageQueueSender {
    private final JwtFilterInterceptor jwtFilterInterceptor;

    public ResizeImageQueueSenderImpl(RabbitTemplate rabbitTemplate, UserService userService, JwtFilterInterceptor jwtFilterInterceptor) {
        super(rabbitTemplate, userService);
        this.jwtFilterInterceptor = jwtFilterInterceptor;
    }

    @Override
    public void resizeImage(ResizeImageRequest request) {
        jwtFilterInterceptor.getJwt().ifPresentOrElse(
                publishResizeImageRequest(request),
                () -> log.debug("Unable to resize image due to missing JWT token"));
    }

    @Override
    public void updateImageAttachment(FileUploadResponse fileUploadResponse) {
        final var message = new DefaultMessage<FileUploadResponse>()
                .setPayload(fileUploadResponse);
        this.sendMessage(message, QueueDefinition.UPDATE_ATTACHMENT_IMAGES_QUEUE);
    }

    @NotNull
    private Consumer<Jwt> publishResizeImageRequest(ResizeImageRequest request) {
        return jwt -> {
            final var message = new AuthenticatedMessage<>();
            message.setToken(jwt.getTokenValue())
                    .setPayload(request);
            this.sendAuthenticatedMessage(message, QueueDefinition.STORAGE_RESIZE_IMAGES_QUEUE);
            log.debug("Resized image successfully");
        };
    }

}
