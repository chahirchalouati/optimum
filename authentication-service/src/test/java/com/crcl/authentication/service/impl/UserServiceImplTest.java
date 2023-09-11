package com.crcl.authentication.service.impl;

import com.crcl.authentication.domain.User;
import com.crcl.authentication.dto.UserDto;
import com.crcl.authentication.mappers.RoleMapper;
import com.crcl.authentication.mappers.UserMapper;
import com.crcl.authentication.mappers.UserMapperImpl;
import com.crcl.authentication.repository.UserRepository;
import com.crcl.authentication.utils.assertions.UserDtoAssertion;
import com.crcl.authentication.utils.builders.UserDtoTestBuilder;
import com.crcl.authentication.utils.builders.UserTestBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleMapper roleMapper;
    @Spy
    private UserMapper userMapper = new UserMapperImpl(roleMapper);
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testSaveUser() {

        UserDto userDto = UserDtoTestBuilder.createUserDto()
                .withUsername("testuser")
                .withPassword("password")
                .withAccountNonExpired()
                .withDefaultAvatar()
                .build();

        User user = UserTestBuilder.fromDto(userDto).build();

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto savedUser = userService.save(userDto);

        UserDtoAssertion.assertThat(savedUser)
                .hasUsername("testuser")
                .hasPassword("password");
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(any(User.class));
        verify(userMapper).toEntity(userDto);
        verify(userMapper).toDto(user);
    }

}