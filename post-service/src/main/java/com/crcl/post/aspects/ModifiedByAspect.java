package com.crcl.post.aspects;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;

@Aspect
@Component
@AllArgsConstructor
public class ModifiedByAspect {

    @Before("@annotation(com.crcl.post.validators.annotation.ModifiedBy)")
    public String getModifiedBy(JoinPoint pjp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(Arrays.toString(pjp.getArgs()));
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    @Before("@annotation(com.crcl.post.annotation.DeletedBy)")
    public void deletedBy(JoinPoint pjp) throws IllegalAccessException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var field = ((Field) pjp.getTarget());
        field.setAccessible(true);
        field.set(pjp.getTarget(), ((UserDetails) authentication.getPrincipal()).getUsername());
    }
}
