package com.crcl.authentication.mappers;

import com.crcl.authentication.domain.Permission;
import com.crcl.authentication.dto.PermissionDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PermissionMapper extends GenericMapper<Permission, PermissionDto> {

}
