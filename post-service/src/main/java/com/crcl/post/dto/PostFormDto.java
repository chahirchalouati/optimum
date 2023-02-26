package com.crcl.post.dto;

import com.crcl.post.domain.Post;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class PostFormDto {
    private Set<MultipartFile> files;
    private String content;
    private Post.Visibility visibility;
}
