package com.crcl.post.dto;

import lombok.Data;

@Data
public class ProfileDto {
    private String username;
    private String email;
    private String avatar;
    private String backgroundImage;
    private UserDto user;
}