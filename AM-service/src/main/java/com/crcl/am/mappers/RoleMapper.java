package com.crcl.am.mappers;

import com.crcl.am.domain.GramifyRole;
import com.crcl.am.dto.RoleDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper extends GenericMapper<GramifyRole, RoleDto> {

}
