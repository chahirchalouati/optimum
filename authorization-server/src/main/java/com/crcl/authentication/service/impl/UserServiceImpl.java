package com.crcl.authentication.service.impl;

import com.crcl.authentication.clients.ProfileClient;
import com.crcl.authentication.clients.ServerStorageClient;
import com.crcl.authentication.domain.GramifyUser;
import com.crcl.authentication.dto.CreateUserRequest;
import com.crcl.authentication.dto.UserDto;
import com.crcl.authentication.helpers.AuthenticationHelper;
import com.crcl.authentication.mappers.UserMapper;
import com.crcl.authentication.repository.UserRepository;
import com.crcl.authentication.service.UserService;
import com.crcl.authentication.utils.ProfileUtils;
import com.crcl.authentication.utils.RoleUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final ProfileClient profileClient;
    private final AuthenticationHelper authenticationHelper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ServerStorageClient serverStorageClient;

    @Override
    public UserDto save(UserDto userDto) {
        log.debug("Saving user: {}", userDto);
        GramifyUser gramifyUser = this.userMapper.toEntity(userDto);
        gramifyUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        gramifyUser.setRoles(RoleUtils.getDefaultUserRoles());
        GramifyUser savedGramifyUser = userRepository.save(gramifyUser);
        log.debug("User saved: {}", savedGramifyUser);
        return userMapper.toDto(savedGramifyUser);
    }

    @Override
    public List<UserDto> saveAll(List<UserDto> entities) {
        log.debug("Saving users: {}", entities);
        List<UserDto> savedUsers = entities.stream()
                .map(this::save).toList();
        log.debug("Users saved: {}", savedUsers);
        return savedUsers;
    }

    @Override
    public void deleteById(String id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setEnabled(false);
            GramifyUser disabledGramifyUser = userRepository.save(user);
            log.debug("User with id {} was disabled: {}", user.getId(), disabledGramifyUser);
        });
    }

    @Override
    public UserDto findById(String id) {
        log.debug("Finding user by id: {}", id);
        UserDto foundUser = userRepository.findById(id)
                .map(userMapper::toDto).orElse(null);
        log.debug("User found: {}", foundUser);
        return foundUser;
    }


    @Override
    public List<UserDto> findAll() {
        log.debug("Finding all users");
        List<UserDto> foundUsers = userRepository.getAll(authenticationHelper.getCurrent());
        log.debug("Users found: {}", foundUsers);
        return foundUsers;
    }


    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        log.debug("Finding users by page: {}", pageable);
        Page<UserDto> foundUsers = userRepository.getAll(pageable, authenticationHelper.getCurrent());
        log.debug("Users found: {}", foundUsers);
        return foundUsers;
    }

    @Override
    public UserDto update(UserDto userDto, String id) {
        log.debug("Updating user with id {}: {}", id, userDto);
        GramifyUser updatedGramifyUser = userRepository.findById(id)
                .map(user -> userMapper.toEntity(userDto))
                .map(userRepository::save)
                .orElse(null);
        UserDto updatedUserDto = userMapper.toDto(updatedGramifyUser);
        log.debug("User updated: {}", updatedUserDto);
        return updatedUserDto;
    }

    @Override
    public UserDto findByUsername(String username) {
        log.debug("Finding user by username: {}", username);
        UserDto foundUser = userRepository.findByUsernameAllIgnoreCase(username)
                .map(userMapper::toDto).orElse(null);
        log.debug("User found: {}", foundUser);
        return foundUser;
    }

    @Override
    public UserDto save(CreateUserRequest request) {
        log.debug("Creating user: {}", request);
        final GramifyUser gramifyUser = userMapper.toEntity(request);
        this.addUserAvatar(request, gramifyUser);
        gramifyUser.setPassword(passwordEncoder.encode(request.getPassword()));
        gramifyUser.setRoles(RoleUtils.getDefaultUserRoles());
        UserDto savedUser = userMapper.toDto(this.userRepository.save(gramifyUser));
        log.debug("User created: {}", savedUser);
        return savedUser;
    }

    @Override
    public UserDto getCurrentUser() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsernameAllIgnoreCase((String) jwt.getClaims().get("username"))
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public Set<UserDto> findByUserNames(Set<String> userNames) {
        List<GramifyUser> gramifyUsers = this.userRepository.findByUsernameIn(userNames);
        this.profileClient.findByUsernames(new ArrayList<>(userNames));
        return new HashSet<>(userMapper.mapToDto(gramifyUsers));
    }

    private void addUserAvatar(CreateUserRequest request, GramifyUser gramifyUser) {
        try {
            if (nonNull(request.getAvatarFile())) {
                var fileSaveResponse = this.serverStorageClient.save(request.getAvatarFile());
                gramifyUser.setAvatar(fileSaveResponse.getLink());
            }
        } catch (Exception e) {
            log.error("An error occurred while saving avatar for user: {}", gramifyUser.getUsername(), e);
            gramifyUser.setAvatar(ProfileUtils.getAvatar(gramifyUser));
        }
    }
}
