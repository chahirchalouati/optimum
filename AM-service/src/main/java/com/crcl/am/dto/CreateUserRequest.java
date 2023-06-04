package com.crcl.am.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateUserRequest extends UserDto {

    private MultipartFile avatarFile;
}
