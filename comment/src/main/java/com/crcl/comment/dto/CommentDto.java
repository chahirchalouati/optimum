package com.crcl.comment.dto;

import com.crcl.comment.domain.Comment;
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

    private LocalDateTime createdAt;
    @JsonIgnore
    private String username;
}
