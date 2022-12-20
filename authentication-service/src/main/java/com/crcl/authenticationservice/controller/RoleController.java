package com.crcl.authenticationservice.controller;

import com.crcl.authenticationservice.dto.RoleDto;
import com.crcl.authenticationservice.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<Page<RoleDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.roleService.findAll(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleDto>> findAll() {
        return ResponseEntity.ok(this.roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.roleService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDto> save(@Valid RoleDto roleDto) {
        return ResponseEntity.ok(this.roleService.save(roleDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<RoleDto>> save(@Valid List<RoleDto> entities) {
        return ResponseEntity.ok(this.roleService.save(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> update(@Valid RoleDto roleDto, String id) {
        return ResponseEntity.ok(this.roleService.update(roleDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.roleService.deleteById(id);
        return ResponseEntity.accepted().build();

    }
}
