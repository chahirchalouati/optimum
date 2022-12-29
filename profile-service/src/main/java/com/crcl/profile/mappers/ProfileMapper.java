package com.crcl.profile.mappers;

import com.crcl.common.utils.generic.GenericMapper;
import com.crcl.profile.domain.Profile;
import com.crcl.profile.dto.ProfileDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProfileMapper extends GenericMapper<Profile, ProfileDto> {

}
