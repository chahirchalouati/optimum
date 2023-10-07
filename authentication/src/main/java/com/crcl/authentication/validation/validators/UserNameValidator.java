package com.crcl.authentication.validation.validators;

import com.crcl.authentication.repository.UserRepository;
import com.crcl.authentication.validation.UniqueUserName;
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
