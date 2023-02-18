package com.crcl.profile.controller;

import com.crcl.profile.dto.ProfileDto;
import com.crcl.profile.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/profiles")
@AllArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<ProfileDto>> findAll() {
        return ResponseEntity.ok(this.profileService.findAll());
    }

    @GetMapping("/example")
    public ResponseEntity<Page<ProfileDto>> findAll(ProfileDto pageRequest, Pageable pageable) {
        return ResponseEntity.ok(this.profileService.findAll(pageRequest, pageable));
    }

    @GetMapping("/example/one")
    public ResponseEntity<ProfileDto> findOne(ProfileDto pageRequest) {
        return ResponseEntity.ok(this.profileService.findOne(pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.profileService.findById(id));
    }

    @GetMapping("/profile/username/{username}")
    public ResponseEntity<ProfileDto> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(this.profileService.findByUsername(username));
    }

    @GetMapping("/profile/usernames")
    public ResponseEntity<List<ProfileDto>> findByUserNames(@RequestParam List<String> usernames) {
        return ResponseEntity.ok(this.profileService.findByUsernames(usernames));
    }

    @PostMapping
    public ResponseEntity<ProfileDto> save(@Valid @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(this.profileService.save(profileDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<ProfileDto>> save(@Valid @RequestBody List<ProfileDto> entities) {
        return ResponseEntity.ok(this.profileService.save(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> update(@Valid @RequestBody ProfileDto profileDto, @PathVariable String id) {
        return ResponseEntity.ok(this.profileService.update(profileDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.profileService.deleteById(id);
        return ResponseEntity.accepted().build();

    }
}
