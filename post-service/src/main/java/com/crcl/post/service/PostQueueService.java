package com.crcl.post.service;

import com.crcl.post.dto.PostDto;
import jakarta.validation.constraints.NotBlank;

public interface PostQueueService {
    @NotBlank
    void publishCreatePostEvent(final PostDto postDto);
}
