package com.crcl.am.mappers;

import com.crcl.am.domain.FriendShip;
import com.crcl.am.dto.FriendShipDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapper.class, RoleMapper.class, PermissionMapper.class}
)
public interface FriendShipMapper extends GenericMapper<FriendShip, FriendShipDto> {
}
