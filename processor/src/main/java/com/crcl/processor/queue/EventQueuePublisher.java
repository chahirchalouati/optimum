package com.crcl.processor.queue;

import com.crcl.core.queue.QueuePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventQueuePublisher extends QueuePublisher {
    public EventQueuePublisher(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }
}
