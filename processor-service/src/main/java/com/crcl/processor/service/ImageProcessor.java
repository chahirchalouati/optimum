package com.crcl.processor.service;

import com.crcl.common.dto.queue.ProcessableImage;
import com.crcl.common.dto.queue.events.AuthenticatedQEvent;

public interface ImageProcessor extends Processor<AuthenticatedQEvent<ProcessableImage>> {
}
