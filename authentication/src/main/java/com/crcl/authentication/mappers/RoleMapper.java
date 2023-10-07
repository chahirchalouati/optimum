package com.crcl.authentication.mappers;

import com.crcl.authentication.domain.Role;
import com.crcl.authentication.dto.RoleDto;
import com.crcl.core.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper extends GenericMapper<Role, RoleDto> {

}
