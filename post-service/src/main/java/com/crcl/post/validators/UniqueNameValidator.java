package com.crcl.post.validators;

import com.crcl.post.repository.PostRepository;
import com.crcl.post.validators.annotation.UniqueName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {
    private final PostRepository postRepository;

    public UniqueNameValidator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
