package com.crcl.userInfo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProfileDto {
    private String username;
    private String avatar;
    private String backgroundImage;
    private UserDto user;
}