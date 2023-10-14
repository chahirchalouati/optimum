package com.crcl.friend_ship.queue.impl;

import com.crcl.core.dto.queue.CreatePostPayload;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import com.crcl.core.utils.ExchangeDefinition;
import com.crcl.core.utils.QueueDefinition;
import com.crcl.core.validation.SecurityContextInterceptor;
import com.crcl.friend_ship.queue.EventQueueConsumer;
import com.crcl.friend_ship.service.ImageProcessor;
import com.crcl.friend_ship.service.VideoProcessor;
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

    @SecurityContextInterceptor
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = ExchangeDefinition.PostExchange.CREATE, type = ExchangeTypes.FANOUT),
            value = @Queue(QueueDefinition.PROCESSABLE_IMAGE_QUEUE)
    ))
    public void consumeProcessableImageEvent(AuthenticatedQEvent<CreatePostPayload> message) {
        imageProcessor.process(message);
    }

    @SecurityContextInterceptor
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name = ExchangeDefinition.PostExchange.CREATE, type = ExchangeTypes.FANOUT),
            value = @Queue(QueueDefinition.PROCESSABLE_VIDEO_QUEUE)
    ))
    public void consumeProcessableVideoEvent(AuthenticatedQEvent<CreatePostPayload> message) {
        videoProcessor.process(message);
    }
}



