package com.crcl.authentication.mappers;

import com.crcl.authentication.domain.User;
import com.crcl.authentication.dto.UserDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {RoleMapper.class, PermissionMapper.class}
)
public interface UserMapper extends GenericMapper<User, UserDto> {

}
