package com.crcl.post.service;

import com.crcl.post.dto.PostDto;

public interface NotificationService {

    void notifyCreatedPost(PostDto payload);
}
