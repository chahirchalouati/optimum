package com.crcl.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RequiredArgsConstructor
public class QueueService {
    protected final RabbitTemplate rabbitTemplate;


}
