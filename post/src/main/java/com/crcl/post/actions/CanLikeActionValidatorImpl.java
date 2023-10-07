package com.crcl.post.actions;

import com.crcl.post.dto.PostDto;
import com.crcl.post.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CanLikeActionValidatorImpl extends ActionValidator {

    public CanLikeActionValidatorImpl(UserService userService) {
        super(userService);
    }

    @Override
    public void validate(PostDto postDto) {
        postDto.getActions().put(Actions.LIKE, true);
    }
}
