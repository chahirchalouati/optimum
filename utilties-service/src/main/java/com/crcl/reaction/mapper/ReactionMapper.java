package com.crcl.reaction.mapper;

import com.crcl.common.utils.generic.GenericMapper;
import com.crcl.reaction.domain.Reaction;
import com.crcl.reaction.dto.ReactionDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReactionMapper extends GenericMapper<Reaction, ReactionDto> {
}
