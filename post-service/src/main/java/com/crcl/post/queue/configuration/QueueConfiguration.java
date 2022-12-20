package com.crcl.post.queue.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QueueConfiguration {

    public static final String UPDATE_PAYMENT_QUEUE = "update_payment_queue";
    public static final String UPDATE_ORDER_STATE_QUEUE = "update_order_state_queue";
    public static final String UPDATE_PAYMENT_STATE_QUEUE = "update_payment_state_queue";
    public static final String NOTIFY_PAYMENT_COMPLETE_QUEUE = "notify_payment_complete_queue";

    private static final List<String> QUEUES = List.of(
            UPDATE_PAYMENT_QUEUE,
            UPDATE_ORDER_STATE_QUEUE,
            UPDATE_PAYMENT_STATE_QUEUE,
            NOTIFY_PAYMENT_COMPLETE_QUEUE
    );

    @Bean
    public Declarables init() {
        final List<Queue> declarables = QUEUES.stream()
                .map(Queue::new)
                .toList();
        return new Declarables(declarables);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter(objectMapper());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

}
