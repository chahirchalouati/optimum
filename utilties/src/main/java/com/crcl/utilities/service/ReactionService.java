package com.crcl.utilities.service;

import com.crcl.core.dto.requests.CreateReactionRequest;
import com.crcl.core.dto.requests.ReactionSearchRequest;
import com.crcl.utilities.dto.ReactionDto;
import org.springframework.data.domain.Page;

public interface ReactionService {
    Page<ReactionDto> findAll(ReactionSearchRequest request);

    ReactionDto create(CreateReactionRequest request);
}
