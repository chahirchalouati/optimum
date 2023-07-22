package com.crcl.reaction.controller;

import com.crcl.common.dto.requests.CreateReactionRequest;
import com.crcl.common.dto.requests.ReactionSearchRequest;
import com.crcl.reaction.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reactions")
@RequiredArgsConstructor
public class ReactionController {
    private final ReactionService reactionService;

    @GetMapping
    public ResponseEntity<?> get(ReactionSearchRequest request) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(CreateReactionRequest request) {
        return ResponseEntity.ok().build();
    }
}

