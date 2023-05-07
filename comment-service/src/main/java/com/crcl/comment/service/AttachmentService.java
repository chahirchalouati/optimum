package com.crcl.comment.service;

import com.crcl.common.dto.queue.ImageUpload;

public interface AttachmentService {
    void updateByEtag(ImageUpload message);
}
