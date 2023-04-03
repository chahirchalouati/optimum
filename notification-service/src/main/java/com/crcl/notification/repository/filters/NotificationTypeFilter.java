package com.crcl.notification.repository.filters;

import com.crcl.notification.domain.NotificationTypeRequest;
import io.micrometer.common.util.StringUtils;
import io.netty.util.internal.StringUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class NotificationTypeFilter {


    public Filter filterFor(NotificationTypeRequest request) {
        return new Filter().fromObject(request);
    }

    public class Filter {
        private final Criteria criteria = new Criteria();

        public Filter() {
        }

        public Filter fromObject(NotificationTypeRequest request) {
            filterByType(criteria, request.getType());
            filterByType(criteria, request.getType());
            filterByType(criteria, request.getType());
            filterByType(criteria, request.getType());
            filterByType(criteria, request.getType());
            return this;
        }

        private void filterByType(Criteria criteria, String type) {
            if (!StringUtils.isNotBlank(type)){
                criteria.andOperator(Criteria.where("type").is(type));
            }
        }

        public Query build() {
            return new Query(criteria);
        }
    }
}
