package com.crcl.post.domain;

import com.crcl.core.dto.UserDto;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Profile {
    private String avatar;
    private String bgImage;
    private UserDto user;
}
