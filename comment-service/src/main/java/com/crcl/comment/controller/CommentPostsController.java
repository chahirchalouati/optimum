package com.crcl.comment.controller;

import com.crcl.comment.dto.CommentDto;
import com.crcl.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comments/post")
@AllArgsConstructor
public class CommentPostsController {
    private final CommentService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Page<CommentDto>> findById(@PathVariable String id, Pageable pageable) {
        return ResponseEntity.ok(this.postService.findByPostId(id, pageable));
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<Integer> countByPost(@PathVariable String postId) {
        return ResponseEntity.ok(this.postService.countByPost(postId));
    }
}
