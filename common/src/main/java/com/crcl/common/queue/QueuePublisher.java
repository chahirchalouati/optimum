package com.crcl.common.queue;

import com.crcl.common.dto.queue.QEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class QueuePublisher {
    private final RabbitTemplate rabbitTemplate;

    public <T> void publish(QEvent<T> QEvent, String queueName) {
        rabbitTemplate.convertAndSend(queueName, QEvent);
    }
}
