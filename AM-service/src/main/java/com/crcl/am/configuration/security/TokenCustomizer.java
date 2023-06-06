package com.crcl.am.configuration.security;

import com.crcl.am.domain.GramifyPermission;
import com.crcl.am.domain.GramifyRole;
import com.crcl.am.domain.GramifyUser;
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
            GramifyUser gramifyUser = (GramifyUser) principal.getPrincipal();
            context.getClaims()
                    .claim(GramifyUser.Fields.id, gramifyUser.getId())
                    .claim(GramifyUser.Fields.email, gramifyUser.getEmail())
                    .claim(GramifyUser.Fields.username, gramifyUser.getUsername())
                    .claim(GramifyUser.Fields.firstName, gramifyUser.getFirstName())
                    .claim(GramifyUser.Fields.lastName, gramifyUser.getLastName())
                    .claim(GramifyUser.Fields.roles, getAuthorities(gramifyUser.getRoles()));
        }
    }

    public Set<String> getAuthorities(Set<GramifyRole> gramifyRoles) {

        Stream<String> permissionsStream = gramifyRoles.stream()
                .map(GramifyRole::getPermissions)
                .flatMap(Set::stream)
                .map(GramifyPermission::getName);

        Stream<String> rolesStream = gramifyRoles.stream()
                .map(GramifyRole::getName);

        return Stream.concat(permissionsStream, rolesStream)
                .collect(toSet());
    }


}
