package com.crcl.post.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {CreatePostRequestValidator.class})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasPostContentOrFiles {
    String message() default "Either payload content or files must be provided.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
