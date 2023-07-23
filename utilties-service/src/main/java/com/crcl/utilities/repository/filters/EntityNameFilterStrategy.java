package com.crcl.utilities.repository.filters;

import com.crcl.common.dto.requests.ReactionSearchRequest;
import com.crcl.utilities.domain.Reaction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class EntityNameFilterStrategy implements FilterStrategy<ReactionSearchRequest> {

    @Override
    public void applyFilters(Query query, ReactionSearchRequest request) {
        if (request.getEntityName() != null) {
            query.addCriteria(Criteria.where(Reaction.Fields.entity).is(request.getEntityName()));
        }
    }
}
