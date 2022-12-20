package com.crcl.post.validators.annotation;

import com.crcl.post.validators.UniqueNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueNameValidator.class)
public @interface UniqueName {
    String message() default "error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
