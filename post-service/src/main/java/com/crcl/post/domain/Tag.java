package com.crcl.post.domain;

import com.crcl.core.dto.UserDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Accessors(chain = true)
public class Tag {
    @Indexed(unique = true)
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
