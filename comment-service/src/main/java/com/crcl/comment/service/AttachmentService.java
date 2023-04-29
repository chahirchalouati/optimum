package com.crcl.comment.service;

import com.crcl.common.dto.queue.DefaultQEvent;
import com.crcl.common.dto.queue.ImageUpload;

public interface AttachmentService {
    void updateByEtag(DefaultQEvent<ImageUpload> message);
}
