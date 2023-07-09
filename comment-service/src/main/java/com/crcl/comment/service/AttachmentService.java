package com.crcl.comment.service;

import com.crcl.common.dto.queue.ProcessableImage;

public interface AttachmentService {
    void updateByEtag(ProcessableImage message);
}
