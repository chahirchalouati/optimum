package com.crcl.am.annotation.validators;

import com.crcl.am.annotation.UniqueEmail;
import com.crcl.am.repository.UserRepository;
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
