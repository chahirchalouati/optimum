package com.crcl.comment.service;

import com.crcl.core.dto.queue.ProcessableImageEvent;

public interface AttachmentService {
    void updateByEtag(ProcessableImageEvent message);
}
