package com.crcl.processor.service;

import com.crcl.core.dto.queue.payloads.CreatePostPayload;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;

public interface ImageProcessor extends Processor<AuthenticatedQEvent<CreatePostPayload>> {
}
