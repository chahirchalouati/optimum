package com.crcl.post.configuration.queue;

import com.crcl.core.configuration.CommonQueueConfiguration;
import com.crcl.core.utils.ExchangeDefinition;
import com.crcl.core.utils.QueueDefinition;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QueueConfiguration extends CommonQueueConfiguration {

    private static final List<String> QUEUES = List.of(
            QueueDefinition.POST_CREATED_QUEUE,
            QueueDefinition.PROCESSABLE_IMAGE_QUEUE,
            QueueDefinition.PROCESSABLE_VIDEO_QUEUE
    );

    @Override
    public List<String> getQueues() {
        return QUEUES;
    }

    @Bean
    public FanoutExchange postExchange() {
        return new FanoutExchange(ExchangeDefinition.POST);
    }

    @Bean
    public Declarables storageBinding(FanoutExchange storageExchange) {

        return new Declarables(

                BindingBuilder
                        .bind(new Queue(QueueDefinition.PROCESSABLE_VIDEO_QUEUE))
                        .to(storageExchange),

                BindingBuilder
                        .bind(new Queue(QueueDefinition.PROCESSABLE_IMAGE_QUEUE))
                        .to(storageExchange)
        );
    }


}
