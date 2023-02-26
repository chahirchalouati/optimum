package com.crcl.processor.queue;

import com.crcl.common.dto.AuthenticatedMessage;
import com.crcl.common.dto.Message;
import com.crcl.processor.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public abstract class MessageQueuePublisher {

    protected final RabbitTemplate rabbitTemplate;
    protected final UserService userService;

    public <T> void publish(Message<T> message, String queueName) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

    public <T> void publishAuthenticatedMessage(AuthenticatedMessage<T> message, String queueName) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
