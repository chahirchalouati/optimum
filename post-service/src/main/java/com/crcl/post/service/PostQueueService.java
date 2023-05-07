package com.crcl.post.service;

import com.crcl.post.dto.PostDto;

public interface PostQueueService {

    void publishCreatePostEvent(final PostDto postDto);
}
