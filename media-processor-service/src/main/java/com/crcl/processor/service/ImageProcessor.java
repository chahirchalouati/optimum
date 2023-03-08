package com.crcl.processor.service;

import com.crcl.common.dto.AuthenticatedQEvent;
import com.crcl.common.queue.ImageUpload;

public interface ImageProcessor extends Processor<AuthenticatedQEvent<ImageUpload>> {
}
