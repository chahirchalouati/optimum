package com.crcl.post.service;

import com.crcl.post.domain.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilesService {
    void handleFiles(List<MultipartFile> request, Post post);
}
