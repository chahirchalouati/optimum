package com.crcl.comment.validators;

import com.crcl.comment.repository.CommentRepository;
import com.crcl.comment.validators.annotation.UniqueName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {
    private final CommentRepository commentRepository;

    public UniqueNameValidator(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
