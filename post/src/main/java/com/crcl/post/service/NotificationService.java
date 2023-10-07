package com.crcl.post.service;

import com.crcl.post.domain.Post;

public interface NotificationService {

    void notifyCreatedPost(Post payload);
}
