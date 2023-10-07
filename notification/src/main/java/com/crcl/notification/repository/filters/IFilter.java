package com.crcl.notification.repository.filters;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public abstract class IFilter<T> {
    protected final List<Criteria> criteria = new ArrayList<>();

    public abstract IFilter filter(T t);

    public Query build() {
        if (criteria.isEmpty()) {
            return new Query();
        }
        return new Query(new Criteria().andOperator(criteria));
    }
}
