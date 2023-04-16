package com.crcl.audit.queue;

import com.crcl.common.queue.QueuePublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventQueuePublisher extends QueuePublisher {
    public EventQueuePublisher(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }
}
