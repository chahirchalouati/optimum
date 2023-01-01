package com.crcl.profile.dto;

import com.crcl.profile.annotation.UniqueEmail;
import com.crcl.profile.annotation.UniqueUserName;
import com.crcl.profile.domain.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProfileDto {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @UniqueUserName
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @UniqueEmail
    private String email;
    private String avatar;
    private String backgroundImage;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDto user;
}