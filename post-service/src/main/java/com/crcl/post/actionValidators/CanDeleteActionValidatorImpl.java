package com.crcl.post.actionValidators;

import com.crcl.post.dto.PostDto;
import com.crcl.post.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CanDeleteActionValidatorImpl extends ActionValidator {


    public CanDeleteActionValidatorImpl(UserService userService) {
        super(userService);
    }

    @Override
    public void validate(PostDto postDto) {
        var currentUser = userService.getCurrentUser();
        var username = postDto.getCreator().getUser().getUsername();
        postDto.getActions().put(Actions.DELETE, Objects.equals(username, currentUser.getUsername()));
    }
}
