package com.crcl.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    @GetMapping
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(List.of("hello", "there"));
    }
}
