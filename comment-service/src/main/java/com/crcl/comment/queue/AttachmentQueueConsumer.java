package com.crcl.comment.queue;

import com.crcl.comment.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class AttachmentQueueConsumer {
    private final AttachmentService attachmentService;

//    @RabbitListener(queues = QueueDefinition.UPDATE_IMAGES_QUEUE)
//    public void consumeImageUploadEvent(DefaultQEvent<ImageUpload> message) {
//        attachmentService.updateByEtag(message.getPayload());
//    }


}
