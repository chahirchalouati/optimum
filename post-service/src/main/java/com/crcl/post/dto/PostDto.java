package com.crcl.post.dto;

import com.crcl.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDto {
    private List<AttachmentDto> attachments;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;
    private String content;
    private ProfileDto owner;
    private Post.Visibility visibility;
    @JsonIgnore
    private String username;
}
