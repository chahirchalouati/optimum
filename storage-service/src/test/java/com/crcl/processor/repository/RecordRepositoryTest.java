package com.crcl.processor.repository;

import com.crcl.processor.service.BaseRepositoryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

class RecordRepositoryTest extends BaseRepositoryConfiguration {
    @Autowired
    RecordRepository recordRepository;
}