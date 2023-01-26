package com.crcl.authentication.repository;

import com.crcl.authentication.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomUserRepository {
    Page<UserDto> getAll(Pageable pageable, UserDto current);

    List<UserDto> getAll(UserDto current);
}
