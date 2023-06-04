package com.crcl.am.mappers;

import com.crcl.am.domain.GramifyUser;
import com.crcl.am.dto.UserDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {RoleMapper.class, PermissionMapper.class}
)
public interface UserMapper extends GenericMapper<GramifyUser, UserDto> {

}
