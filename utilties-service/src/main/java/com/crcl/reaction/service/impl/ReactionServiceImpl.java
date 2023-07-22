package com.crcl.reaction.service.impl;

import com.crcl.reaction.dto.ReactionDto;
import com.crcl.reaction.mapper.ReactionMapper;
import com.crcl.reaction.repository.ReactionRepository;
import com.crcl.reaction.service.ReactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final ReactionMapper reactionMapper;

    @Override
    public ReactionDto save(ReactionDto reactionDto) {
        return null;
    }

    @Override
    public List<ReactionDto> saveAll(List<ReactionDto> entitiesDto) {
        return null;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public ReactionDto findById(String s) {
        return null;
    }

    @Override
    public List<ReactionDto> findAll() {
        return null;
    }

    @Override
    public Page<ReactionDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ReactionDto update(ReactionDto reactionDto, String s) {
        return null;
    }
}
