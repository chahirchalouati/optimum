package com.crcl.comment.clients;

import com.crcl.comment.configuration.OAuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "${client.srvPost.name}",
        url = "${client.srvPost.url}",
        configuration = OAuthFeignConfig.class
)
public interface SrvPostClient {
}
