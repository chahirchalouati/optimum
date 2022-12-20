package com.crcl.post.aspects;

import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@AllArgsConstructor
public class ModifiedByAspect {

    @Around("@annotation(com.crcl.post.validators.annotation.ModifiedBy)")
    public String getModifiedBy(ProceedingJoinPoint pjp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(Arrays.toString(pjp.getArgs()));
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }
}
