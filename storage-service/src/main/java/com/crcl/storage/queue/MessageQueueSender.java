package com.crcl.storage.queue;

import com.crcl.common.dto.AuthenticatedMessage;
import com.crcl.storage.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public abstract class MessageQueueSender {

    protected final RabbitTemplate rabbitTemplate;
    protected final UserService userService;

    public <T> void sendMessage(T message, String queueName) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

    public <T> void sendAuthenticatedMessage(AuthenticatedMessage<T> message, String queueName) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
