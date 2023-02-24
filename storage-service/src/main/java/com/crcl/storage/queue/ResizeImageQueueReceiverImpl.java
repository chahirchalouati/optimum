package com.crcl.storage.queue;

import com.crcl.common.dto.AuthenticatedMessage;
import com.crcl.common.utils.QueueDefinition;
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

    @RabbitListener(queues = QueueDefinition.STORAGE_RESIZE_IMAGES_QUEUE)
    public void onReceiveResizeImage(Message<AuthenticatedMessage<ResizeImageRequest>> message) {
        ResizeImageRequest request = message.getPayload().getPayload();
        this.imageResizeService.resize(request);
    }
}
