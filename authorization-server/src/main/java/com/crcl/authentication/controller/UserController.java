package com.crcl.authentication.controller;

import com.crcl.authentication.dto.UserDto;
import com.crcl.authentication.service.UserService;
import com.crcl.authentication.views.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping
    @JsonView(UserView.UserResponseView.class)
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDto> findAll(Pageable pageable) {
        Page<UserDto> page = this.userService.findAll(pageable);
        log.debug("Page object: {}", page);
        return page;
    }

    @GetMapping("/all")
    @JsonView(UserView.UserResponseView.class)
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/usernames")
    @JsonView(UserView.UserResponseView.class)
    public ResponseEntity<Set<UserDto>> findByUserNames(@RequestParam("usernames") Set<String> userNames) {
        return ResponseEntity.ok(this.userService.findByUserNames(userNames));
    }

    @GetMapping("/{id}")
    @JsonView(UserView.UserResponseView.class)
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @GetMapping("/username/{username}")
    @JsonView(UserView.UserResponseView.class)
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(this.userService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody
                                        @JsonView(UserView.CreateUserView.class) UserDto userDto) {
        return ResponseEntity.ok(this.userService.save(userDto));
    }

    @PostMapping("/many")
    public ResponseEntity<List<UserDto>> save(@Valid @RequestBody @JsonView(UserView.CreateUserView.class) List<UserDto> entities) {
        return ResponseEntity.ok(this.userService.saveAll(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@Valid @RequestBody @JsonView(UserView.CreateUserView.class) UserDto userDto, String id) {
        return ResponseEntity.ok(this.userService.update(userDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.userService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
