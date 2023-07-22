package com.crcl.profile.controller;

import com.crcl.profile.dto.ProfileDto;
import com.crcl.profile.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/searches")
@AllArgsConstructor
public class ProfileSearchRestController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<Page<ProfileDto>> findAll(ProfileDto pageRequest, Pageable pageable) {
        return ResponseEntity.ok(this.profileService.findAll(pageRequest, pageable));
    }

    @GetMapping("/username")
    public ResponseEntity<ProfileDto> findByUsername(@RequestParam String username) {
        return ResponseEntity.ok(this.profileService.findByUsername(username));
    }

    @GetMapping("/usernames")
    public ResponseEntity<List<ProfileDto>> findByUserNames(@RequestParam List<String> usernames) {
        return ResponseEntity.ok(this.profileService.findByUsernames(usernames));
    }
}
