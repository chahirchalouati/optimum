package com.crcl.friend_ship.service;

import com.crcl.core.dto.queue.CreatePostPayload;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;

public interface VideoProcessor extends Processor<AuthenticatedQEvent<CreatePostPayload>> {

}
