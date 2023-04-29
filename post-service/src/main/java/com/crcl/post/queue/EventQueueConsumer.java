package com.crcl.post.queue;

import com.crcl.common.dto.queue.DefaultQEvent;
import com.crcl.common.dto.queue.ImageUpload;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.post.synchronizers.ImageUploadSynchronizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class EventQueueConsumer {
    private final ImageUploadSynchronizer synchronizer;

    @RabbitListener(queues = QueueDefinition.UPDATE_IMAGES_QUEUE)
    public void consumeImageUploadEvent(Message<DefaultQEvent<ImageUpload>> message) {
        this.synchronizer.synchronize(message.getPayload());
    }
}
