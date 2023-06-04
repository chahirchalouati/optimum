package com.crcl.am.mappers;

import com.crcl.am.domain.GramifyPermission;
import com.crcl.am.dto.PermissionDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PermissionMapper extends GenericMapper<GramifyPermission, PermissionDto> {

}
