package com.crcl.am.helpers;

import com.crcl.am.dto.UserDto;
import com.crcl.am.mappers.UserMapper;
import com.crcl.am.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
@AllArgsConstructor
public class AuthenticationHelperImpl implements AuthenticationHelper {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getCurrent() {

        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (authentication != null) {
            if (authentication instanceof JwtAuthenticationToken authenticationToken) {
                String username = authenticationToken.getToken().getClaim("username");
                return nonNull(username) ? this.findByUsername(username) : null;
            }
        }
        return null;
    }

    private UserDto findByUsername(String username) {
        return userRepository.findByUsernameAllIgnoreCase(username)
                .map(userMapper::toDto)
                .orElse(null);
    }
}
