package com.crcl.processor.queue;

import com.crcl.common.dto.AuthenticatedQEvent;
import com.crcl.common.dto.QEvent;
import com.crcl.processor.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public abstract class EventQueuePublisher {

    protected final RabbitTemplate rabbitTemplate;
    protected final UserService userService;

    public <T> void publish(QEvent<T> QEvent, String queueName) {
        rabbitTemplate.convertAndSend(queueName, QEvent);
    }

    public <T> void publishAuthenticatedMessage(AuthenticatedQEvent<T> message, String queueName) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
