package com.crcl.processor.service;

import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;

public interface ImageProcessor extends Processor<AuthenticatedQEvent<ProcessableImage>> {
}
