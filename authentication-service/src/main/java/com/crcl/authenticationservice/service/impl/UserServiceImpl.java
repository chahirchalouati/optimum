package com.crcl.authenticationservice.service.impl;

import com.crcl.authenticationservice.domain.User;
import com.crcl.authenticationservice.dto.UserDto;
import com.crcl.authenticationservice.mappers.UserMapper;
import com.crcl.authenticationservice.repository.UserRepository;
import com.crcl.authenticationservice.service.UserService;
import com.crcl.authenticationservice.utils.RoleUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto save(UserDto userDto) {
        User user = this.userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(RoleUtils.getDefaultUserRoles());
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> save(List<UserDto> entities) {
        return entities.stream().map(this::save).toList();
    }

    @Override
    public void deleteById(String id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setEnabled(false);
            userRepository.save(user);
            log.info("user with id %s was disabled".formatted(user.getId()));
        });

    }

    @Override
    public UserDto findById(String id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }


    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    public UserDto update(UserDto userDto, String id) {
        return userRepository.findById(id)
                .map(user -> userMapper.toEntity(userDto))
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElse(null);
    }
}
