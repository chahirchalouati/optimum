package com.crcl.comment.queue;

import com.crcl.comment.service.AttachmentService;
import com.crcl.common.dto.DefaultQEvent;
import com.crcl.common.queue.ImageUpload;
import com.crcl.common.utils.QueueDefinition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class AttachmentQueueConsumer {
    private final AttachmentService attachmentService;

    @RabbitListener(queues = QueueDefinition.UPDATE_ATTACHMENT_IMAGES_QUEUE)
    public void consumeImageUploadEvent(Message<DefaultQEvent<ImageUpload>> message) {
        attachmentService.updateByEtag(message.getPayload());
    }


}
