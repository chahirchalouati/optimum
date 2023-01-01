package com.crcl.post.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Data
public class PostFormDto {

    private Set<MultipartFile> files;
    private String content;
    private Set<Tag> tags = new HashSet<>();
}
