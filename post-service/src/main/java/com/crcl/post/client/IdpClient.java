package com.crcl.post.client;

import com.crcl.post.configuration.OAuthFeignConfig;
import com.crcl.post.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${client.idp.name}", url = "${client.idp.url}", configuration = OAuthFeignConfig.class)
public interface IdpClient {
    @GetMapping("/users/username/{username}")
    UserDto findByUsername(@PathVariable String username);
}
