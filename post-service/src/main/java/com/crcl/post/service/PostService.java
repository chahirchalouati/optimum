package com.crcl.post.service;

import com.crcl.common.utils.generic.GenericService;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.dto.PostDto;

public interface PostService extends GenericService<PostDto, String> {

    PostDto save(CreatePostRequest request);
}
