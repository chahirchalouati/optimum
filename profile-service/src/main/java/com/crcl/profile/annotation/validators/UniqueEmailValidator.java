package com.crcl.profile.annotation.validators;

import com.crcl.profile.annotation.UniqueEmail;
import com.crcl.profile.repository.ProfileRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Object> {

    private final ProfileRepository profileRepository;

    public UniqueEmailValidator(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public boolean isValid(Object email, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
