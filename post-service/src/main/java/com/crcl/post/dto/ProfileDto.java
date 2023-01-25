package com.crcl.post.dto;

import lombok.Data;

@Data
public class ProfileDto {
    private String avatar;
    private String backgroundImage;
    private UserDto user;
}