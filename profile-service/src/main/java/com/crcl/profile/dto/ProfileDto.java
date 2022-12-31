package com.crcl.profile.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Accessors(chain = true)
public class ProfileDto {
    @Indexed(unique = true, background = true, direction = IndexDirection.DESCENDING)
    private String username;
    @Indexed(unique = true, background = true, direction = IndexDirection.DESCENDING)
    private String email;
    private String avatar;
    private String backgroundImage;
}