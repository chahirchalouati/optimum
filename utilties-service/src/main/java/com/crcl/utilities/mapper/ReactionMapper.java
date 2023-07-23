package com.crcl.utilities.mapper;

import com.crcl.common.dto.requests.CreateReactionRequest;
import com.crcl.common.utils.generic.GenericMapper;
import com.crcl.utilities.domain.Reaction;
import com.crcl.utilities.dto.ReactionDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReactionMapper extends GenericMapper<Reaction, ReactionDto> {

    Reaction toEntity(CreateReactionRequest request);
}
