package com.crcl.authentication.validation.validators;

import com.crcl.authentication.repository.UserRepository;
import com.crcl.authentication.validation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

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
