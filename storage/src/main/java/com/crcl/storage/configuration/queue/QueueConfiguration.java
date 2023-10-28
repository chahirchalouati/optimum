package com.crcl.storage.configuration.queue;

import com.crcl.core.configuration.CommonQueueConfiguration;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QueueConfiguration extends CommonQueueConfiguration {
    private static final List<String> QUEUES = List.of(

    );

    @Override
    public List<String> getQueues() {
        return QUEUES;
    }
}
