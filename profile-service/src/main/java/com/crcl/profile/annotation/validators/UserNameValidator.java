package com.crcl.profile.annotation.validators;


import com.crcl.profile.annotation.UniqueUserName;
import com.crcl.profile.repository.ProfileRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<UniqueUserName, Object> {
    private final ProfileRepository userRepository;

    public UserNameValidator(ProfileRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(Object username, ConstraintValidatorContext constraintValidatorContext) {
        return !this.userRepository.existsByUsernameIgnoreCase(String.valueOf(username));
    }
}
