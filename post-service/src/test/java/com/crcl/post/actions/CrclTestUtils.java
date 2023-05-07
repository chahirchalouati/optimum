package com.crcl.post.actions;

import com.crcl.post.domain.Access;
import com.crcl.post.dto.PostDto;

public class CrclTestUtils {
    public static PostDto buildPostDto(String content) {
        return new PostDto()
                .setAccess(Access.PUBLIC)
                .setContent(content);
    }
}
