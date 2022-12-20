package com.crcl.post.queue;

import com.crcl.post.queue.request.MessageRequest;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QueueSender {

    private final RabbitTemplate rabbitTemplate;

    public <T> void sendMessage(MessageRequest<T> message, String queueName) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

}
