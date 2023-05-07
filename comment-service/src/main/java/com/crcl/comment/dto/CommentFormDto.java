package com.crcl.comment.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class CommentFormDto {
    private Set<MultipartFile> files;
    private String content;
    @NotNull(message = "postId can't be null")
    private String postId;
}
