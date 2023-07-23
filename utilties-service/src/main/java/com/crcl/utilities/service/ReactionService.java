package com.crcl.utilities.service;

import com.crcl.common.dto.requests.CreateReactionRequest;
import com.crcl.common.dto.requests.ReactionSearchRequest;
import com.crcl.utilities.dto.ReactionDto;
import org.springframework.data.domain.Page;

public interface ReactionService {
    Page<ReactionDto> findAll(ReactionSearchRequest request);

    ReactionDto create(CreateReactionRequest request);
}
