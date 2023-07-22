package com.crcl.processor.service;

import com.crcl.processor.domain.FileRecord;
import com.crcl.processor.repository.RecordRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;


public class ImageDtoResizeServiceImplTest extends BaseRepositoryConfiguration {

    @Autowired
    private RecordRepository recordRepository;

    @Before
    public void setUp() {
        recordRepository.deleteAll().subscribe();
    }

    @Test
    public void test() {
        List<Integer> integers = IntStream.range(0, 10).boxed().toList();
        Flux.fromIterable(integers)
                .flatMap(this::buildFileRecord)
                .map(recordRepository::save)
                .subscribe();

        Flux<FileRecord> records = recordRepository.findAll();

        StepVerifier.create(records)
                .expectRecordedMatches(fileRecords -> Objects.equals(fileRecords.size(), 10))
                .expectComplete()
                .verify();
    }

    private Mono<FileRecord> buildFileRecord(Integer integer) {
        FileRecord bucketName = new FileRecord()
                .setName("name_ " + integer)
                .setBucket("bucket name_ " + integer);
        return Mono.just(bucketName);
    }
}
