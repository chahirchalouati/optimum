package com.crcl.post.dto;

import com.crcl.common.domain.Visibility;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class PostFormDto {
    private Set<MultipartFile> files;
    private String content;
    private Visibility visibility;
}
