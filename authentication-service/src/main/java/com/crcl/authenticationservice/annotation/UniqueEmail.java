package com.crcl.authenticationservice.annotation;

import com.crcl.authenticationservice.annotation.validators.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Chahir Chalouati
 */
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "email already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean isUpdate() default false;
}
