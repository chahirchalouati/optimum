package com.crcl.notification.configuration.queue;

import com.crcl.core.helper.LocalDateTimeSerializer;
import com.crcl.core.queue.SortDeserializer;
import com.crcl.core.utils.QueueDefinition;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class QueueConfiguration {

    private static final List<String> QUEUES = List.of(
            QueueDefinition.NOTIFY_POST_CREATED_QUEUE
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
        objectMapper.registerModule(new PageJacksonModule());
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Sort.class, new SortDeserializer());
        objectMapper.registerModule(simpleModule);
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

}
