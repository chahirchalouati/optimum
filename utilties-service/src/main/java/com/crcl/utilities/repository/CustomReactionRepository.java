package com.crcl.utilities.repository;

import com.crcl.common.dto.requests.ReactionSearchRequest;
import com.crcl.utilities.dto.ReactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomReactionRepository {
    Page<ReactionDto> find(ReactionSearchRequest request, Pageable pageable);
}
