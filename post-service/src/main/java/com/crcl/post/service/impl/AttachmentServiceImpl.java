package com.crcl.post.service.impl;

import com.crcl.common.dto.DefaultMessage;
import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.properties.ImageSize;
import com.crcl.common.queue.ImageUploadEvent;
import com.crcl.post.repository.AttachmentRepository;
import com.crcl.post.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Override
    public void updateByEtag(DefaultMessage<ImageUploadEvent> message) {
        ImageUploadEvent imageUploadEvent = message.getPayload();
        FileUploadResult response = imageUploadEvent.getResponse();
        log.info("Updating attachment with eTag: " + response.getEtag());
        attachmentRepository.findByEtag(imageUploadEvent.getId()).stream().findFirst().ifPresent(
                attachment -> {
                    ImageSize imageSize = imageUploadEvent.getImageSize();
                    attachment.getAdditionalData().put(imageSize.getName(), response);
                    attachmentRepository.save(attachment);
                    log.info("Attachment with id " + attachment.getId() + " updated successfully.");
                }
        );
    }
}
