package com.crcl.utilities.controller;

import com.crcl.common.dto.requests.CreateReactionRequest;
import com.crcl.common.dto.requests.ReactionSearchRequest;
import com.crcl.utilities.service.ReactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reactions")
@RequiredArgsConstructor
public class ReactionController {

    private final ReactionService reactionService;

    @GetMapping
    public ResponseEntity<?> get(ReactionSearchRequest request) {
        return ResponseEntity.ok(reactionService.findAll(request));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateReactionRequest request) {
        return ResponseEntity.ok(reactionService.create(request));
    }
}

