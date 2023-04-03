package com.crcl.post.service;

import com.crcl.common.utils.NotificationTargets;
import com.crcl.post.dto.PostDto;

public interface NotificationService {

    void notifyCreatedPost(NotificationTargets targets, PostDto payload);
}
