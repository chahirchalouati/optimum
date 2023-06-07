package com.crcl.authentication.annotation.validators;

import com.crcl.authentication.annotation.UniqueUserName;
import com.crcl.authentication.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<UniqueUserName, Object> {
    private final UserRepository userRepository;

    public UserNameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(Object username, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userRepository.existsByUsernameIgnoreCase(String.valueOf(username));
    }
}
