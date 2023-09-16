package com.crcl.core.queue;

import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.queue.events.QEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class QueuePublisher {

    private final RabbitTemplate rabbitTemplate;

    private   <T, E extends QEvent<T, E>> void publish(QEvent<T, E> event, String queueName) {
        rabbitTemplate.convertAndSend(queueName, event);
    }

    public <T> void publishMessage(DefaultQEvent<T> event, String queueName) {
        publish(event,queueName);
    }

    public <T> void publishAuthenticatedMessage(AuthenticatedQEvent<T> event, String queueName) {
        publish(event,queueName);
    }
}
