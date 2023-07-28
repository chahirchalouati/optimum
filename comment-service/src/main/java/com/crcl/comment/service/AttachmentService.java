package com.crcl.comment.service;

import com.crcl.core.dto.queue.ProcessableImage;

public interface AttachmentService {
    void updateByEtag(ProcessableImage message);
}
