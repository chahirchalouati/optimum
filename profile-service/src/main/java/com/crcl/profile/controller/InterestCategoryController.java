package com.crcl.profile.controller;

import com.crcl.profile.dto.InterestCategoryDto;
import com.crcl.profile.service.InterestCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("interestCategories")
public class InterestCategoryController {

    private final InterestCategoryService interestCategoryService;

    @PostMapping
    public ResponseEntity<InterestCategoryDto> save(@Valid @RequestBody InterestCategoryDto interestCategoryDto) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/many")
    public ResponseEntity<List<InterestCategoryDto>> saveAll(@Valid @RequestBody List<InterestCategoryDto> entitiesDto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<InterestCategoryDto> findById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<InterestCategoryDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterestCategoryDto> update(@Valid @RequestBody InterestCategoryDto interestCategoryDto, @PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
