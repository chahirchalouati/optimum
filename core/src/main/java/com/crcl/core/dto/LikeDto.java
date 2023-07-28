package com.crcl.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikeDto {
    private UserDto user;
    private LocalDateTime createdAt;
    private Boolean like;
    private Boolean dislike;
}
