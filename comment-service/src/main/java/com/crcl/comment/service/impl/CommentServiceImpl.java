package com.crcl.comment.service.impl;

import com.crcl.comment.domain.Comment;
import com.crcl.comment.dto.CommentDto;
import com.crcl.comment.exceptions.utils.CommentExceptionUtils;
import com.crcl.comment.mappers.CommentMapper;
import com.crcl.comment.repository.CommentRepository;
import com.crcl.comment.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    @Override
    public CommentDto save(CommentDto commentDto) {
        return this.mapper.toOptionalEntity(commentDto)
                .map(commentRepository::save)
                .map(mapper::toDto)
                .orElse(null);

    }

    @Override
    public List<CommentDto> saveAll(List<CommentDto> entities) {
        return entities.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (this.commentRepository.existsById(id)) {
            this.commentRepository.deleteById(id);
            return;
        }
        CommentExceptionUtils.commentNotFoundException(id).get();
    }

    @Override
    public CommentDto findById(Long id) {
        return this.commentRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(CommentExceptionUtils.commentNotFoundException(id));
    }

    @Override
    public List<CommentDto> findAll() {
        return this.commentRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CommentDto> findAll(Pageable pageable) {
        Page<Comment> commentPage = this.commentRepository.findAll(pageable);
        commentPage.getContent().forEach(System.out::println);
        return commentPage
                .map(mapper::toDto);
    }

    @Override
    public CommentDto update(CommentDto commentDto, Long id) {
        Comment comment = this.commentRepository.findById(id)
                .orElseThrow(CommentExceptionUtils.commentNotFoundException(id));
        Comment partialUpdate = this.mapper.partialUpdate(commentDto, comment);
        Comment savedComment = this.commentRepository.save(partialUpdate);
        return mapper.toDto(savedComment);
    }

}
