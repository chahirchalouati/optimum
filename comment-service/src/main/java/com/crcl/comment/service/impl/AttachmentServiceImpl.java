package com.crcl.comment.service.impl;

import com.crcl.comment.repository.AttachmentRepository;
import com.crcl.comment.service.AttachmentService;
import com.crcl.core.dto.ResizedImageDetails;
import com.crcl.core.dto.queue.ProcessableImageEvent;
import com.crcl.core.dto.responses.FileUploadResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Override
    public void updateByEtag(ProcessableImageEvent processableImageEvent) {
        FileUploadResult response = processableImageEvent.getResult();
        log.info("Updating attachment with eTag: " + response.getEtag());
        attachmentRepository.findByEtag(processableImageEvent.getId()).stream().findFirst().ifPresent(
                attachment -> {
                    var imageSize = processableImageEvent.getSize();
                    var details = new ResizedImageDetails()
                            .setDetails(response)
                            .setDimensions(imageSize);
                    attachment.setOrientation(processableImageEvent.getOrientation());
                    attachment.getAdditionalData().put(imageSize.getName(), details);
                    attachmentRepository.save(attachment);
                    log.info("Attachment with id " + attachment.getId() + " updated successfully.");
                }
        );
    }
}
