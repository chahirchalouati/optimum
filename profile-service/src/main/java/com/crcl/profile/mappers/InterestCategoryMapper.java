package com.crcl.profile.mappers;

import com.crcl.common.utils.generic.GenericMapper;
import com.crcl.profile.domain.InterestCategory;
import com.crcl.profile.dto.InterestCategoryDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InterestCategoryMapper extends GenericMapper<InterestCategory, InterestCategoryDto> {

}
