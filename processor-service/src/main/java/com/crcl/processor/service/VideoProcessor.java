package com.crcl.processor.service;

import com.crcl.common.dto.queue.ProcessableVideo;
import com.crcl.common.dto.queue.events.AuthenticatedQEvent;

public interface VideoProcessor extends Processor<AuthenticatedQEvent<ProcessableVideo>> {

}
