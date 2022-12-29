package com.crcl.authenticationservice.annotation.validators;

import com.crcl.authenticationservice.annotation.UniqueEmail;
import com.crcl.authenticationservice.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Object> {

    private final UserRepository userRepository;

    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(Object email, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userRepository.existsByEmailIgnoreCase(String.valueOf(email));
    }
}
