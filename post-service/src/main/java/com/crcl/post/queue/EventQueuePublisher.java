package com.crcl.post.queue;

import com.crcl.core.dto.queue.events.QEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventQueuePublisher {

    private final RabbitTemplate rabbitTemplate;

    public <T> void sendMessage(QEvent<T> QEvent, String queueName) {
        rabbitTemplate.convertAndSend(queueName, QEvent);
    }

}
