package com.crcl.utilities.repository.filters;

import org.springframework.data.mongodb.core.query.Query;

public interface FilterStrategy<T> {
    void applyFilters(Query query, T request);
}