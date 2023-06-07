package com.crcl.authentication.mappers;

import com.crcl.authentication.domain.GramifyRole;
import com.crcl.authentication.dto.RoleDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper extends GenericMapper<GramifyRole, RoleDto> {

}
