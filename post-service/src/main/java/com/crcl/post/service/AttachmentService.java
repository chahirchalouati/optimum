package com.crcl.post.service;

import com.crcl.common.dto.DefaultMessage;
import com.crcl.common.queue.ImageUploadEvent;

public interface AttachmentService {
    void updateByEtag(DefaultMessage<ImageUploadEvent> message);
}
