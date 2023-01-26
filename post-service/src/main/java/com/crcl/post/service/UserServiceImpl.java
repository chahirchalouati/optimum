package com.crcl.post.service;

import com.crcl.post.clients.IdpClient;
import com.crcl.post.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final IdpClient idpClient;

    public UserDto getCurrentUser() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return idpClient.findByUsername((String) jwt.getClaims().get("username"));
    }

    @Override
    public List<UserDto> getUsersByUserName(Set<String> usersNames) {
        return idpClient.findByUsername(usersNames);
    }
}
