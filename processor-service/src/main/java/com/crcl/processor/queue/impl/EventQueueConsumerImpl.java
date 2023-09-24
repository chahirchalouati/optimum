package com.crcl.processor.queue.impl;

import com.crcl.core.dto.queue.CreatePostPayload;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import com.crcl.core.utils.ExchangeDefinition;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.processor.queue.EventQueueConsumer;
import com.crcl.processor.service.ImageProcessor;
import com.crcl.processor.service.VideoProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventQueueConsumerImpl implements EventQueueConsumer {

    private final ImageProcessor imageProcessor;
    private final VideoProcessor videoProcessor;

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = ExchangeDefinition.POST, type = ExchangeTypes.FANOUT),
            value = @Queue(QueueDefinition.PROCESSABLE_IMAGE_QUEUE)
    ))
    public void consumeProcessableImageEvent(AuthenticatedQEvent<CreatePostPayload> message) {
        System.out.println(message.getPayload().files().size());
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = ExchangeDefinition.POST, type = ExchangeTypes.FANOUT),
            value = @Queue(QueueDefinition.PROCESSABLE_VIDEO_QUEUE)
    ))
    public void consumeProcessableVideoEvent(AuthenticatedQEvent<CreatePostPayload> message) {
        System.out.println(message.getPayload().files().size());
    }
}



