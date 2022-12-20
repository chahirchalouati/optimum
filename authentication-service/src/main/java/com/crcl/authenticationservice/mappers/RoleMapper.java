package com.crcl.authenticationservice.mappers;

import com.crcl.authenticationservice.domain.Role;
import com.crcl.authenticationservice.dto.RoleDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper extends GenericMapper<Role, RoleDto> {

}
