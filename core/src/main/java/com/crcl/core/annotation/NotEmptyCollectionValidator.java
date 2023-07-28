package com.crcl.core.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class NotEmptyCollectionValidator implements ConstraintValidator<NotEmptyCollection, Collection<?>> {
    @Override
    public boolean isValid(Collection collection, ConstraintValidatorContext context) {
        return !CollectionUtils.isEmpty(collection);
    }
}
