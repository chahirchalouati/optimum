package com.crcl.post.service;

import com.crcl.common.dto.DefaultQEvent;
import com.crcl.common.utils.QueueDefinition;
import com.crcl.post.dto.PostDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PostQueueServiceImpl extends PostQueueService {


    public PostQueueServiceImpl(RabbitTemplate rabbitTemplate, UserService userService) {
        super(rabbitTemplate, userService);
    }

    @Override
    protected void publishCreatePostEvent(PostDto postDto) {
        var headers = new HashMap<String, Object>(1);
        headers.put("token", userService.getToken());

        var event = new DefaultQEvent<PostDto>()
                .setHeaders(headers)
                .setPayload(postDto);

        this.rabbitTemplate.convertAndSend(QueueDefinition.POST_CREATED_QUEUE, event);
    }
}
