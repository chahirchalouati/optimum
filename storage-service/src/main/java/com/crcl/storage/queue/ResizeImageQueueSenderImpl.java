package com.crcl.storage.queue;

import com.crcl.storage.configuration.queue.QueueConfiguration;
import com.crcl.storage.dto.ResizeImageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ResizeImageQueueSenderImpl extends ResizeImageQueueSender {

    public ResizeImageQueueSenderImpl(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    @Override
    public void resizeImage(ResizeImageRequest request) {
        this.sendMessage(request, QueueConfiguration.STORAGE_RESIZE_IMAGES_QUEUE);
    }
}
