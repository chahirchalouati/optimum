package com.crcl.comment.service.impl;

import com.crcl.comment.repository.AttachmentRepository;
import com.crcl.comment.service.AttachmentService;
import com.crcl.common.dto.ResizedImageDetails;
import com.crcl.common.dto.queue.ImageUpload;
import com.crcl.common.dto.responses.FileUploadResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Override
    public void updateByEtag(ImageUpload imageUpload) {
        FileUploadResult response = imageUpload.getResult();
        log.info("Updating attachment with eTag: " + response.getEtag());
        attachmentRepository.findByEtag(imageUpload.getId()).stream().findFirst().ifPresent(
                attachment -> {
                    var imageSize = imageUpload.getSize();
                    var details = new ResizedImageDetails()
                            .setDetails(response)
                            .setDimensions(imageSize);
                    attachment.setOrientation(imageUpload.getOrientation());
                    attachment.getAdditionalData().put(imageSize.getName(), details);
                    attachmentRepository.save(attachment);
                    log.info("Attachment with id " + attachment.getId() + " updated successfully.");
                }
        );
    }
}
