package com.crcl.post.client;

import com.crcl.core.dto.UserDto;
import com.crcl.post.configuration.security.OAuthFeignConfig;
import com.crcl.post.fallbacks.IdpClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(
        name = "${client.authentication.name}",
        url = "${client.authentication.url}",
        fallbackFactory = IdpClientFallbackFactory.class,
        configuration = OAuthFeignConfig.class)
public interface IdpClient {
    @GetMapping("/users/username/{username}")
    UserDto findByUsername(@PathVariable String username);

    @GetMapping("/users/usernames")
    List<UserDto> findByUsername(@RequestParam("usernames") Set<String> usernames);
}
