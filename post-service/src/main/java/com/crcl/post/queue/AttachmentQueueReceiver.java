package com.crcl.post.queue;

import com.crcl.common.dto.DefaultMessage;
import com.crcl.common.queue.ImageUploadEvent;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.post.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class AttachmentQueueReceiver {
    private final AttachmentService attachmentService;

    @RabbitListener(queues = QueueDefinition.UPDATE_ATTACHMENT_IMAGES_QUEUE)
    public void updateAttachmentQueue(Message<DefaultMessage<ImageUploadEvent>> message) {
        attachmentService.updateByEtag(message.getPayload());
    }


}
