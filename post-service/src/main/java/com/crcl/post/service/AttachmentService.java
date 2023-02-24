package com.crcl.post.service;

import com.crcl.common.dto.responses.FileUploadResponse;

public interface AttachmentService {
    void updateByEtag(String eTag, FileUploadResponse uploadResponse);
}
