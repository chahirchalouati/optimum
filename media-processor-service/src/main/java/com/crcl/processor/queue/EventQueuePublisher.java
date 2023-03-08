package com.crcl.processor.queue;

import com.crcl.common.annotation.EventQueue;
import com.crcl.common.dto.QEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@EventQueue
@RequiredArgsConstructor
public class EventQueuePublisher {

    private final RabbitTemplate rabbitTemplate;

    public <T> void publish(QEvent<T> QEvent, String queueName) {
        rabbitTemplate.convertAndSend(queueName, QEvent);
    }

}
