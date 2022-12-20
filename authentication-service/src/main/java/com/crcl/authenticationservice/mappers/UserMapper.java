package com.crcl.authenticationservice.mappers;

import com.crcl.authenticationservice.domain.User;
import com.crcl.authenticationservice.dto.UserDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper extends GenericMapper<User, UserDto> {

}
