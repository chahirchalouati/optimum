package com.crcl.profile.controller;

import com.crcl.profile.dto.ProfileDto;
import com.crcl.profile.service.ProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/profiles")
@AllArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    @SneakyThrows
    public ResponseEntity<Page<ProfileDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.profileService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<ProfileDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.profileService.findById(id));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<ProfileDto> save(@Valid @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(this.profileService.save(profileDto));
    }

    @PostMapping("/many")
    @SneakyThrows
    public ResponseEntity<List<ProfileDto>> save(@Valid @RequestBody List<ProfileDto> entities) {
        return ResponseEntity.ok(this.profileService.saveAll(entities));
    }

    @PutMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<ProfileDto> update(@Valid @RequestBody ProfileDto profileDto, @PathVariable String id) {
        return ResponseEntity.ok(this.profileService.update(profileDto, id));
    }

    @DeleteMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.profileService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
