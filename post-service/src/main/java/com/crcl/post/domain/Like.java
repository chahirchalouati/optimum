package com.crcl.post.domain;

import com.crcl.core.dto.UserDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Like {
    private UserDto user;
    private LocalDateTime createdAt;
    private Boolean like;
    private Boolean dislike;
}
