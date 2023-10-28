package com.crcl.storage.repository;

import com.crcl.storage.service.BaseRepositoryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public class RecordRepositoryTest extends BaseRepositoryConfiguration {
    @Autowired
    private final RecordRepository recordRepository;

    RecordRepositoryTest(RecordRepository recordRepository) {
        super();
        this.recordRepository = recordRepository;

    }
}