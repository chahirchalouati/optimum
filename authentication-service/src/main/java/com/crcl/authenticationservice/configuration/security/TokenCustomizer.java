package com.crcl.authenticationservice.configuration.security;

import com.crcl.authenticationservice.domain.Permission;
import com.crcl.authenticationservice.domain.Role;
import com.crcl.authenticationservice.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    private final Function<Set<Role>, List<String>> mapRoles = roles -> roles.stream()
            .map(Role::getName)
            .collect(Collectors.toList());

    @Override

    public void customize(JwtEncodingContext context) {
        Authentication principal = context.getPrincipal();
        if (Objects.equals(context.getTokenType().getValue(), "access_token") && principal instanceof UsernamePasswordAuthenticationToken) {
            final User user = (User) principal.getPrincipal();
            context.getClaims()
                    .claim("email", user.getEmail())
                    .claim("username", user.getUsername())
                    .claim("firstName", user.getFirstName())
                    .claim("lastName", user.getLastName())
                    .claim("email", user.getLastName())
                    .claim("roles", getAuthorities(user.getRoles()));
        }
    }

    public Set<String> getAuthorities(Set<Role> roles) {
        final Stream<String> permissionsStream = roles.stream()
                .map(Role::getPermissions)
                .flatMap(Set::stream)
                .map(Permission::getName);

        final Stream<String> rolesStream = roles.stream()
                .map(Role::getName);

        return Stream.concat(permissionsStream, rolesStream)
                .collect(Collectors.toSet());
    }


}
