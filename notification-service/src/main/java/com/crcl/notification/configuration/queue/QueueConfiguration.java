package com.crcl.notification.configuration.queue;

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

    public static final String NOTIFY_MAIL_QUEUE = "notify_mail_queue";
    public static final String NOTIFY_PUSH_QUEUE = "notify_push_queue";
    public static final String NOTIFY_SMS_QUEUE = "notify_sms_queue";

    private static final List<String> QUEUES = List.of(
            NOTIFY_MAIL_QUEUE,
            NOTIFY_PUSH_QUEUE,
            NOTIFY_SMS_QUEUE
    );

    @Bean
    public Declarables init() {
        final List<Queue> declarables = QUEUES.stream()
                .map(Queue::new).toList();
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
