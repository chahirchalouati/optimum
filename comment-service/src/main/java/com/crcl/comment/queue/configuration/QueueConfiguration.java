package com.crcl.comment.queue.configuration;

import com.crcl.common.utils.QueueDefinition;
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

    private static final List<String> QUEUES = List.of(
            QueueDefinition.PROCESSABLE_IMAGE_QUEUE,
            QueueDefinition.UPDATE_IMAGES_QUEUE,
            QueueDefinition.PROCESSABLE_VIDEO_QUEUE
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
