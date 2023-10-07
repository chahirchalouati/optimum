package com.crcl.audit.controller;

import com.crcl.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("audits")
@RequiredArgsConstructor
public class AuditController {
    private final AuditService auditService;

    @PreAuthorize("#oauth2.hasScope('read_audit')")
    @GetMapping
    public ResponseEntity getAll(Pageable pageable) {
        return ResponseEntity.ok(auditService.findAll(pageable));
    }
}
