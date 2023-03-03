package com.crcl.post.service;

import com.crcl.post.dto.PostDto;
import com.crcl.post.dto.PostFormDto;

public interface PostService extends GenericService<PostDto, Long> {
    PostDto save(PostFormDto postFormDto);

    boolean existsById(Long id);
}
