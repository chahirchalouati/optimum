package com.crcl.post.service.impl;

import com.crcl.common.dto.responses.FileUploadResponse;
import com.crcl.post.domain.Attachment;
import com.crcl.post.repository.AttachmentRepository;
import com.crcl.post.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Override
    public void updateByEtag(String eTag, FileUploadResponse uploadResponse) {
        log.info("Updating attachment with eTag: " + eTag);
        attachmentRepository.findByEtag(eTag).stream().findFirst().ifPresent(
                attachment -> {
                    Attachment target = new Attachment();
                    BeanUtils.copyProperties(attachment, target, "id");
                    target.setName(uploadResponse.getName());
                    attachmentRepository.save(target);
                    log.info("Attachment with id " + target.getId() + " updated successfully.");
                }
        );
    }
}
