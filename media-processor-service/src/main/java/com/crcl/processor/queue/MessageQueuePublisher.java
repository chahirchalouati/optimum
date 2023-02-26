package com.crcl.processor.queue;

import com.crcl.common.dto.Message;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageQueuePublisher {

    private final RabbitTemplate rabbitTemplate;

    public <T> void publish(Message<T> message, String queueName) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

}
