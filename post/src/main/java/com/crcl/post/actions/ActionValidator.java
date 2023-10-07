package com.crcl.post.actions;

import com.crcl.post.dto.PostDto;
import com.crcl.post.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ActionValidator {
    protected final UserService userService;

    public abstract void validate(PostDto postDto);
}
