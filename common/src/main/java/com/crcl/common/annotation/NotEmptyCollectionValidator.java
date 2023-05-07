package com.crcl.common.annotation;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class NotEmptyCollectionValidator implements ConstraintValidator<NotEmptyCollection, Collection<?>> {
    @Override
    public boolean isValid(Collection collection, ConstraintValidatorContext context) {
        return !CollectionUtils.isEmpty(collection);
    }
}
