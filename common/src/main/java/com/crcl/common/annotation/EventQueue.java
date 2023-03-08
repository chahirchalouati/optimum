package com.crcl.common.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Role(BeanDefinition.ROLE_APPLICATION)
public @interface EventQueue {

    @AliasFor(annotation = Component.class)
    String value() default "";

    @AliasFor(annotation = Configuration.class)
    Class<?>[] classes() default {};

    @Bean
    String name() default "";

    @Bean
    String[] aliases() default {};

    @Bean
    @Autowired
    String[] dependencies() default {};

    // Add your custom configuration properties here
}
