package com.crcl.utilities.repository.filters;

import com.crcl.core.dto.requests.ReactionSearchRequest;
import com.crcl.utilities.domain.Reaction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class TypeFilterStrategy implements FilterStrategy<ReactionSearchRequest> {
    @Override
    public void applyFilters(Query query, ReactionSearchRequest request) {
        if (request.getType() != null) {
            query.addCriteria(Criteria.where(Reaction.Fields.type).is(request.getType()));
        }
    }
}