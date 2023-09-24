package com.crcl.core.queue;

import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import com.crcl.core.dto.queue.events.DefaultQEvent;
import com.crcl.core.dto.queue.events.QEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public abstract class QueuePublisher {

    private final RabbitTemplate rabbitTemplate;

    private <T, E extends QEvent<T, E>> void publish(QEvent<T, E> event, String queueName) {
        log.debug("Publishing " + event.getClass().getSimpleName() + " to queue '{}' - {}", queueName, event);
        rabbitTemplate.convertAndSend(queueName, event);
    }

    private <T, E extends QEvent<T, E>> void publish(QEvent<T, E> event, String exchange, String routingKey) {
        log.debug("Publishing " + event.getClass().getSimpleName() + " to exchange '{}' with routing key '{}' - {}", exchange, routingKey, event);
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
    }

    public <T> void publishMessage(DefaultQEvent<T> event, String queueName) {
        publish(event, queueName);
    }

    public <T> void publishMessage(DefaultQEvent<T> event, String exchange, String routingKey) {
        publish(event, exchange, routingKey);
    }

    public <T> void publishAuthenticatedMessage(AuthenticatedQEvent<T> event, String queueName) {
        publish(event, queueName);
    }

    public <T> void publishAuthenticatedMessage(AuthenticatedQEvent<T> event, String exchange, String routingKey) {
        publish(event, exchange, routingKey);
    }
}

