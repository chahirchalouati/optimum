package com.crcl.authenticationservice.mappers;

import com.crcl.authenticationservice.domain.Permission;
import com.crcl.authenticationservice.dto.PermissionDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PermissionMapper extends GenericMapper<Permission, PermissionDto> {

}
