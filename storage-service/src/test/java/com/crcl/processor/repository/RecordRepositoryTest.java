package com.crcl.processor.repository;

import com.crcl.processor.service.BaseRepositoryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public  class RecordRepositoryTest extends BaseRepositoryConfiguration {
    @Autowired
   private final RecordRepository recordRepository;

    RecordRepositoryTest(RecordRepository recordRepository) {
        super();
        this.recordRepository = recordRepository;

    }
}