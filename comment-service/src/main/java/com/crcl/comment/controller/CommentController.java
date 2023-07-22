package com.crcl.comment.controller;

import com.crcl.comment.dto.CommentDto;
import com.crcl.comment.dto.CommentFormDto;
import com.crcl.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<Page<CommentDto>> findAll(@PathVariable("postId") String postId, Pageable pageable) {
        return ResponseEntity.ok(this.commentService.findByPostId(postId, pageable));
    }

    @GetMapping("/{postId}/count")
    public ResponseEntity<Integer> countByPost(@PathVariable("postId") String postId) {
        return ResponseEntity.ok(this.commentService.countByPost(postId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.commentService.findById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@ModelAttribute @Valid CommentFormDto commentFormDto) {
        return ResponseEntity.ok(this.commentService.save(commentFormDto));
    }

    @PostMapping("/many")
    public ResponseEntity<List<CommentDto>> save(@Valid @RequestBody List<CommentDto> entities) {
        return ResponseEntity.ok(this.commentService.saveAll(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@Valid @RequestBody CommentDto userDto, Long id) {
        return ResponseEntity.ok(this.commentService.update(userDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.commentService.deleteById(id);
        return ResponseEntity.accepted().build();

    }
}
