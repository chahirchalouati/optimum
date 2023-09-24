package com.crcl.core.dto;

import com.crcl.core.domain.TagKind;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TagDto {
    private String name;
    private boolean system = false;
    private TagKind kind;
    private UserDto userDto;
}
