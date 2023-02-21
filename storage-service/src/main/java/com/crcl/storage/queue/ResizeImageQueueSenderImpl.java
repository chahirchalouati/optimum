package com.crcl.storage.queue;

import com.crcl.common.dto.AuthenticatedMessage;
import com.crcl.storage.configuration.queue.QueueConfiguration;
import com.crcl.storage.dto.ResizeImageRequest;
import com.crcl.storage.service.UserService;
import com.crcl.storage.utils.ServerSecurityToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResizeImageQueueSenderImpl extends ResizeImageQueueSender {


    public ResizeImageQueueSenderImpl(RabbitTemplate rabbitTemplate, UserService userService) {
        super(rabbitTemplate, userService);
    }

    @Override
    public void resizeImage(ResizeImageRequest request) {
        final var message = new AuthenticatedMessage<>();
        final var token = ServerSecurityToken.getToken();
        message.setToken(token)
                .setPayload(request);
        this.sendAuthenticatedMessage(message, QueueConfiguration.STORAGE_RESIZE_IMAGES_QUEUE);
    }
}
