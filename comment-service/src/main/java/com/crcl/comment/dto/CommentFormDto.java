package com.crcl.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class CommentFormDto {
    private Set<MultipartFile> files;
    private String content;
    @NotNull(message = "postId can't be null")
    private String postId;
}
