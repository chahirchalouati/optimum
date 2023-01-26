package com.crcl.authentication.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProfileDto {
    private String username;
    private String email;
    private String avatar;
    private String backgroundImage;
    private UserDto user;
}