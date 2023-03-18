package com.crcl.post.client;


import com.crcl.post.configuration.OAuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "${client.comment.name}",
        url = "${client.comment.url}",
        configuration = OAuthFeignConfig.class
)
public interface CommentClient {

    @GetMapping("/comments/post/count/{postId}")
    int countByPost(@PathVariable String postId);

}
