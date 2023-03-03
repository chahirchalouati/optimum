package com.crcl.post.controller;

import com.crcl.post.dto.PostDto;
import com.crcl.post.dto.PostFormDto;
import com.crcl.post.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.postService.findAll(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> findAll() {
        return ResponseEntity.ok(this.postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.postService.findById(id));
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        return ResponseEntity.ok(this.postService.existsById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@ModelAttribute PostFormDto postFormDto) {
        return ResponseEntity.ok(this.postService.save(postFormDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<PostDto>> save(@Valid @RequestBody List<PostDto> entities) {
        return ResponseEntity.ok(this.postService.saveAll(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> update(@Valid @RequestBody PostDto userDto, Long id) {
        return ResponseEntity.ok(this.postService.update(userDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        this.postService.deleteById(id);
        return ResponseEntity.accepted().build();

    }
}
