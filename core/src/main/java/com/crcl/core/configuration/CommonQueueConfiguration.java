package com.crcl.core.configuration;

import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@Import(CommonRabbitMQConfiguration.class)
public abstract class CommonQueueConfiguration {

    protected List<Queue> queues = new ArrayList<>();

    public abstract List<String> getQueues();

    @Bean("queues")
    public Declarables init() {
        queues = getQueues().stream()
                .map(Queue::new)
                .toList();
        return new Declarables(queues);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }

    protected Queue getQueue(String queue) {
        return queues.stream()
                .filter(queue1 -> queue1.getName().equals(queue)).findFirst()
                .orElse(null);
    }
}
