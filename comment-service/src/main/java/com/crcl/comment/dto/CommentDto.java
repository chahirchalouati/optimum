package com.crcl.comment.dto;

import com.crcl.comment.domain.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDto {
    private List<AttachmentDto> attachments;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
    private String content;
    private ProfileDto owner;
    private Comment.Visibility visibility;
    @JsonIgnore
    private String username;
}
