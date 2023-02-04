package com.crcl.comment.dto;

import lombok.Data;

@Data
public final class CommentDto {
    private Long id;
    private String postId;
    private String userId;
    private String content;
}
