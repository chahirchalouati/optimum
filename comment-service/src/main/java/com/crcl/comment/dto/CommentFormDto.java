package com.crcl.comment.dto;

import com.crcl.comment.domain.Comment;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class CommentFormDto {
    private Set<MultipartFile> files;
    private String content;
    private Comment.Visibility visibility;
}
