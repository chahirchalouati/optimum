package com.crcl.post.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class PostFormDto {

    private Set<MultipartFile> files;
    private String content;
}
