package com.crcl.notification.domain;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Data
public class NotificationTypeRequest {
    private String type;
    private Sort sort;
    private int size = 10;
    private int page = 0;

    public Pageable getPageRequest() {
        if (Objects.nonNull(sort)) {
            return PageRequest.of(this.getPage(), this.getSize(), this.getSort());
        }
        return PageRequest.of(this.getPage(), this.getSize());
    }
}
