package com.crcl.storage.queue;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public abstract class MessageQueueSender {

    private final RabbitTemplate rabbitTemplate;

    public <T> void sendMessage(T message, String queueName) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

}
