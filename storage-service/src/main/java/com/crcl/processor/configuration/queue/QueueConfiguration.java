package com.crcl.processor.configuration.queue;

import com.crcl.core.configuration.CommonQueueConfiguration;
import com.crcl.core.utils.QueueDefinition;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QueueConfiguration extends CommonQueueConfiguration {
    private static final List<String> QUEUES = List.of(
            QueueDefinition.PROCESSABLE_IMAGE_QUEUE,
            QueueDefinition.UPDATE_IMAGES_QUEUE
    );

    @Override
    public List<String> getQueues() {
        return QUEUES;
    }
}
