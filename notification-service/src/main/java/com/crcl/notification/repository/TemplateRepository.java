package com.crcl.notification.repository;

import com.crcl.notification.domain.Template;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TemplateRepository extends ReactiveMongoRepository<Template, String> {
}
