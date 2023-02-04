package com.crcl.comment.controller;

import com.crcl.comment.dto.CommentDto;
import com.crcl.comment.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("comments")
@AllArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentDto> findAll(Pageable pageable) {
        return this.commentService.findAll(pageable);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommentDto>> findAll() {
        return ResponseEntity.ok(this.commentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.commentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CommentDto> save(@Valid @RequestBody CommentDto userDto) {
        return ResponseEntity.ok(this.commentService.save(userDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<CommentDto>> save(@Valid @RequestBody List<CommentDto> entities) {
        return ResponseEntity.ok(this.commentService.save(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@Valid @RequestBody CommentDto userDto, Long id) {
        return ResponseEntity.ok(this.commentService.update(userDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        this.commentService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}

