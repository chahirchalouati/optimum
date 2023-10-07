package com.crcl.processor.service;

import com.crcl.core.dto.queue.CreatePostPayload;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;

public interface VideoProcessor extends Processor<AuthenticatedQEvent<CreatePostPayload>> {

}
