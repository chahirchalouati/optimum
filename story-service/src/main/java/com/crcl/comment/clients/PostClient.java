package com.crcl.comment.clients;

import com.crcl.comment.configuration.FeignFormConfig;
import com.crcl.comment.configuration.OAuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${client.post.name}", url = "${client.post.url}",
        configuration = {
                OAuthFeignConfig.class,
                FeignFormConfig.class
        })
public interface PostClient {

    @GetMapping("/posts/{id}")
    boolean existsById(@PathVariable Long id);
}
