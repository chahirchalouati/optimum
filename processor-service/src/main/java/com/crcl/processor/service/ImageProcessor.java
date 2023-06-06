package com.crcl.processor.service;

import com.crcl.common.dto.queue.ImageUpload;
import com.crcl.common.dto.queue.events.AuthenticatedQEvent;

public interface ImageProcessor extends Processor<AuthenticatedQEvent<ImageUpload>> {
}
