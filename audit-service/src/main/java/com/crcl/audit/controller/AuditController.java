package com.crcl.audit.controller;

import com.crcl.audit.domain.Audit;
import com.crcl.audit.repository.AuditRepository;
import net.datafaker.Faker;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
@RequestMapping("/audits")
public class AuditController {
    private final AuditRepository auditRepository;
    private final Faker faker = new Faker();

    public AuditController(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @GetMapping
    public Slice<Audit> get(Pageable pageable) {
        IntStream.range(0, 1000)
                .mapToObj(value -> {
                    Audit audit = new Audit();
                    audit.setPublisher(faker.lorem().paragraph());
                    audit.setTitle(faker.lorem().paragraph());
                    return audit;
                }).parallel()
                .forEach(auditRepository::save);

        return auditRepository.findAll(pageable);
    }
}
