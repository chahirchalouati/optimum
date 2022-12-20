package com.crcl.authenticationservice.controller;

import com.crcl.authenticationservice.dto.UserDto;
import com.crcl.authenticationservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.userService.findAll(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@Valid UserDto userDto) {
        return ResponseEntity.ok(this.userService.save(userDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<UserDto>> save(@Valid List<UserDto> entities) {
        return ResponseEntity.ok(this.userService.save(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@Valid UserDto userDto, String id) {
        return ResponseEntity.ok(this.userService.update(userDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.userService.deleteById(id);
        return ResponseEntity.accepted().build();

    }
}
