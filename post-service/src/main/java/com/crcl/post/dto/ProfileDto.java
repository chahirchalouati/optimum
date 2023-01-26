package com.crcl.post.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileDto {
    private String avatar;
    private String backgroundImage;
    private UserDto user;
}