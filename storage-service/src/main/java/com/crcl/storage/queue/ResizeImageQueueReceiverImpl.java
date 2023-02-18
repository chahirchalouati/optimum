package com.crcl.storage.queue;

import com.crcl.storage.dto.ResizeImageRequest;
import com.crcl.storage.service.ImageResizeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ResizeImageQueueReceiverImpl implements ResizeImageQueueReceiver {
    private final ImageResizeService imageResizeService;

    @RabbitListener(queues = "#{queueConfiguration.STORAGE_RESIZE_IMAGES_QUEUE}")
    public void onReceiveResizeImage(Message<ResizeImageRequest> message) {
        this.imageResizeService.resize(message.getPayload());
    }
}
