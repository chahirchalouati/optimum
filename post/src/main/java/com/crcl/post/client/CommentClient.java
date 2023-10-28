package com.crcl.post.client;


import com.crcl.post.configuration.security.OAuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(
        name = "${client.comment.name}",
        url = "${client.comment.url}",
        configuration = OAuthFeignConfig.class
)
public interface CommentClient {

    @GetMapping("/comments/{postId}/count")
    int countByPost(@PathVariable("postId") String postId);

    @GetMapping("/comments/posts/count")
    Map<String, Long> countByPosts(@RequestParam("postIds") List<String> postIds);

}
