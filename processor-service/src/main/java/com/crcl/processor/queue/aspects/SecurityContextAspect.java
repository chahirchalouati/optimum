package com.crcl.processor.queue.aspects;

import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class SecurityContextAspect {
    private final JwtDecoder decoder;

    @Around("@annotation(com.crcl.core.validation.SecurityContextInterceptor)")
    public Object applySecurityContext(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("Applying security context to message");
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof AuthenticatedQEvent payload) {
            try {
                Jwt jwt = decoder.decode(payload.getToken());
                if (Objects.requireNonNull(jwt.getExpiresAt()).isBefore(Instant.now())) {
                    String errorMsg = "The given token is expired " + jwt.getClaims().toString();
                    log.error(errorMsg);
                    return joinPoint.proceed();
                }
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(new JwtAuthenticationToken(jwt));
                log.debug("Authentication token set in SecurityContext");
            } catch (JwtException | IllegalArgumentException e) {
                log.error("Error occurred while decoding token: {}", e.getMessage());
                return joinPoint.proceed();
            }
        }
        Object result;
        try {
            result = joinPoint.proceed();
        } finally {
            SecurityContextHolder.clearContext();
            log.debug("Authentication token removed from SecurityContext");
        }

        log.debug("Security context applied successfully");
        return result;
    }
}
