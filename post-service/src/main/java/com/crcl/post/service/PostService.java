package com.crcl.post.service;

import com.crcl.post.domain.PostFormDto;
import com.crcl.post.dto.PostDto;

public interface PostService extends GenericService<PostDto, Long> {
    PostDto save(PostFormDto postFormDto);
}
