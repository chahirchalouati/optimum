package com.crcl.comment.controller;

import com.crcl.comment.dto.CommentDto;
import com.crcl.comment.dto.CommentFormDto;
import com.crcl.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService postService;

    @GetMapping
    public ResponseEntity<Page<CommentDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.postService.findAll(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommentDto>> findAll() {
        return ResponseEntity.ok(this.postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.postService.findById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@ModelAttribute CommentFormDto commentFormDto) {
        return ResponseEntity.ok(this.postService.save(commentFormDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<CommentDto>> save(@Valid @RequestBody List<CommentDto> entities) {
        return ResponseEntity.ok(this.postService.saveAll(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@Valid @RequestBody CommentDto userDto, Long id) {
        return ResponseEntity.ok(this.postService.update(userDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        this.postService.deleteById(id);
        return ResponseEntity.accepted().build();

    }
}
