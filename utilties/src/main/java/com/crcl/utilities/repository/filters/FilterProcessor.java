package com.crcl.utilities.repository.filters;

import org.springframework.data.mongodb.core.query.Query;

public class FilterProcessor<T> {
    private final Query query;

    public FilterProcessor() {
        this.query = new Query();
    }

    public FilterProcessor<T> applyFilters(T request, FilterStrategy<T> filterStrategy) {
        filterStrategy.applyFilters(query, request);
        return this;
    }

    public Query getQuery() {
        return query;
    }
}

