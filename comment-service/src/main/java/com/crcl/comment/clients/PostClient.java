package com.crcl.comment.clients;

import com.crcl.comment.configuration.security.FeignFormConfig;
import com.crcl.comment.configuration.security.OAuthFeignConfig;
import com.crcl.core.dto.PostDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "${client.post.name}", url = "${client.post.url}",
        fallbackFactory = PostClientFallBackFactory.class,
        configuration = {
                OAuthFeignConfig.class,
                FeignFormConfig.class
        })
public interface PostClient {

    @GetMapping("/posts/{id}")
    Optional<PostDto> getPostById(@PathVariable String id);
}
