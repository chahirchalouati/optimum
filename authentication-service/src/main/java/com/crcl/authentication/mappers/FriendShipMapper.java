package com.crcl.authentication.mappers;

import com.crcl.authentication.domain.FriendShip;
import com.crcl.authentication.dto.FriendShipDto;
import com.crcl.common.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FriendShipMapper extends GenericMapper<FriendShip, FriendShipDto> {
}
