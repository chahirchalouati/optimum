package com.crcl.utilities.repository;

import com.crcl.common.dto.requests.ReactionSearchRequest;
import com.crcl.utilities.domain.Reaction;
import com.crcl.utilities.dto.ReactionDto;
import com.crcl.utilities.mapper.ReactionMapper;
import com.crcl.utilities.repository.filters.EntityNameFilterStrategy;
import com.crcl.utilities.repository.filters.FilterProcessor;
import com.crcl.utilities.repository.filters.TargetIdFilterStrategy;
import com.crcl.utilities.repository.filters.TypeFilterStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomReactionRepositoryImpl implements CustomReactionRepository {

    private final MongoTemplate mongoTemplate;
    private final ReactionMapper reactionMapper;

    @Override
    public Page<ReactionDto> find(ReactionSearchRequest request, Pageable pageable) {
        FilterProcessor<ReactionSearchRequest> filterProcessor = new FilterProcessor<>();
        filterProcessor.applyFilters(request, new EntityNameFilterStrategy())
                .applyFilters(request, new TargetIdFilterStrategy())
                .applyFilters(request, new TypeFilterStrategy());

        long totalCount = mongoTemplate.count(filterProcessor.getQuery(), Reaction.class);
        filterProcessor.getQuery().with(pageable);

        List<Reaction> reactions = mongoTemplate.find(filterProcessor.getQuery(), Reaction.class);
        List<ReactionDto> reactionsDto = reactionMapper.mapToDto(reactions);

        return new PageImpl<>(reactionsDto, pageable, totalCount);

    }
}
