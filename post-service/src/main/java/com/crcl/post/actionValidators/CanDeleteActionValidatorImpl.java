package com.crcl.post.actionValidators;

import com.crcl.post.dto.PostDto;
import com.crcl.post.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CanDeleteActionValidatorImpl extends ActionValidator {


    public CanDeleteActionValidatorImpl(UserService userService) {
        super(userService);
    }

    @Override
    public void validate(PostDto postDto) {
        postDto.getActions().put(Actions.DELETE, true);
    }
}
