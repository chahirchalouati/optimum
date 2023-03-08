package com.crcl.post.service;

import com.crcl.common.dto.DefaultQEvent;
import com.crcl.common.queue.ImageUpload;

public interface AttachmentService {
    void updateByEtag(DefaultQEvent<ImageUpload> message);
}
