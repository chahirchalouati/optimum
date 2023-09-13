package com.crcl.post.service;

import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;

public interface FilesService {
    void handleFiles(CreatePostRequest request, Post post);
}
