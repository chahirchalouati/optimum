package com.crcl.common.dto.requests;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageableSearchRequest {
    protected int page = 0;
    protected int size = 10;
    protected Sort sort = Sort.unsorted();
}
