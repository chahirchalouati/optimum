package com.crcl.authenticationservice.controller;

import com.crcl.authenticationservice.dto.PermissionDto;
import com.crcl.authenticationservice.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permissions")
@AllArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<Page<PermissionDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.permissionService.findAll(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PermissionDto>> findAll() {
        return ResponseEntity.ok(this.permissionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(this.permissionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PermissionDto> save(@Valid PermissionDto permissionDto) {
        return ResponseEntity.ok(this.permissionService.save(permissionDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<PermissionDto>> save(@Valid List<PermissionDto> entities) {
        return ResponseEntity.ok(this.permissionService.save(entities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDto> update(@Valid PermissionDto permissionDto, String id) {
        return ResponseEntity.ok(this.permissionService.update(permissionDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        this.permissionService.deleteById(id);
        return ResponseEntity.accepted().build();

    }
}
