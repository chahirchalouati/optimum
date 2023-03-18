package com.crcl.comment.dto;

import com.crcl.comment.domain.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDto {
    private Long id;
    private String postId;
    private String content;
    private List<AttachmentDto> attachments;
    private ProfileDto profile;
    private Comment.Visibility visibility;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
    @JsonIgnore
    private String username;
}
