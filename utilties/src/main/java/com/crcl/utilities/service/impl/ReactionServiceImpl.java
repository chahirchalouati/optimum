package com.crcl.utilities.service.impl;

import com.crcl.core.dto.UserDto;
import com.crcl.core.dto.requests.CreateReactionRequest;
import com.crcl.core.dto.requests.ReactionSearchRequest;
import com.crcl.utilities.domain.Reaction;
import com.crcl.utilities.dto.ReactionDto;
import com.crcl.utilities.mapper.ReactionMapper;
import com.crcl.utilities.repository.ReactionRepository;
import com.crcl.utilities.service.ReactionService;
import com.crcl.utilities.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final ReactionMapper reactionMapper;
    private final UserService userService;

    @Override
    public Page<ReactionDto> findAll(ReactionSearchRequest request) {
        return reactionRepository.find(request, Pageable.unpaged());
    }

    @Override
    public ReactionDto create(CreateReactionRequest request) {
        final Reaction entity = reactionMapper.toEntity(request);
        final UserDto currentUser = userService.getCurrentUser();
        return reactionRepository.findByReaction(request.getEntity(),
                        request.getTargetId(),
                        currentUser.getUsername(),
                        currentUser.getEmail(),
                        request.getType(),
                        false)
                .map(reactionMapper::toDto)
                .orElseGet(() -> {
                    entity.setReactingUser(currentUser);
                    return reactionMapper.toDto(reactionRepository.save(entity));
                });


    }
}
