package com.crcl.notification.repository.filters;

import com.crcl.notification.domain.NotificationTypeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

@Component
public class NotificationTypeFilter extends IFilter<NotificationTypeRequest> {

    @Override
    public IFilter<NotificationTypeRequest> filter(NotificationTypeRequest request) {
        filterByType(request.getType());
        return this;
    }

    private void filterByType(String type) {
        if (StringUtils.isNotBlank(type)) {
            criteria.add(Criteria.where("type").is(type));
        }
    }
}
