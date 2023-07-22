package com.crcl.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TagDto {
    private String name;
    private boolean system = false;
    private TagKind kind;
    private UserDto userDto;

    public enum TagKind {
        USER,
        POST,
        COMMENT,
        REPLY,
        PAGE,
        GROUP,
        STORY
    }
}
