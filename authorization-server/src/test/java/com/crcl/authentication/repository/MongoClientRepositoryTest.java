package com.crcl.authentication.repository;

import com.crcl.authentication.domain.GramifyClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MongoClientRepositoryTest extends BaseRepositoryConfiguration {
    @Autowired
    MongoClientRepository mongoClientRepository;

    @Test
    public void test() {
        List<GramifyClient> clients = IntStream.range(0, 10)
                .mapToObj(value -> new GramifyClient().setClientId("client_" + value))
                .toList();
        mongoClientRepository.saveAll(clients);

        Optional<GramifyClient> storedClient = mongoClientRepository.findByClientId("client_1");

        assertTrue(storedClient.isPresent());
        assertEquals(storedClient.get().getClientId(), "client_1");
    }

}
