package com.crcl.authenticationservice.annotation.validators;

import com.crcl.authenticationservice.annotation.UniqueUserName;
import com.crcl.authenticationservice.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
