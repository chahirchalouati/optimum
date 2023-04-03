package com.crcl.post.service;

import com.crcl.post.dto.PostDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public abstract class PostQueueService extends QueueService {
    protected final UserService userService;

    public PostQueueService(RabbitTemplate rabbitTemplate, UserService userService) {
        super(rabbitTemplate);
        this.userService = userService;
    }


    public abstract void publishCreatePostEvent(final PostDto postDto);
}
