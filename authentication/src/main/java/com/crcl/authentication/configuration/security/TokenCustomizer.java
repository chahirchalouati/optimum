package com.crcl.authentication.configuration.security;

import com.crcl.authentication.domain.GramifyPermission;
import com.crcl.authentication.domain.Role;
import com.crcl.authentication.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.ACCESS_TOKEN;


public class TokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {
        final Authentication principal = context.getPrincipal();
        boolean isToken = Objects.equals(context.getTokenType().getValue(), ACCESS_TOKEN);
        if (isToken && principal instanceof UsernamePasswordAuthenticationToken) {
            User user = (User) principal.getPrincipal();
            context.getClaims()
                    .claim(User.Fields.id, user.getId())
                    .claim(User.Fields.email, user.getEmail())
                    .claim(User.Fields.username, user.getUsername())
                    .claim(User.Fields.firstName, user.getFirstName())
                    .claim(User.Fields.lastName, user.getLastName())
                    .claim(User.Fields.roles, getAuthorities(user.getRoles()));
        }
    }

    public Set<String> getAuthorities(Set<Role> roles) {

        Stream<String> permissionsStream = roles.stream()
                .map(Role::getPermissions)
                .flatMap(Set::stream)
                .map(GramifyPermission::getName);

        Stream<String> rolesStream = roles.stream()
                .map(Role::getName);

        return Stream.concat(permissionsStream, rolesStream)
                .collect(toSet());
    }


}
