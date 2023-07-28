package com.crcl.userInfo.client;

import com.crcl.core.dto.UserDto;
import com.crcl.userInfo.configuration.security.OAuthFeignConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@ReactiveFeignClient(
        name = "${client.authentication.name}",
        url = "${client.authentication.url}",
        configuration = OAuthFeignConfig.class)
public interface IdpClient {
    @GetMapping("/users/username/{username}")
    Mono<UserDto> findByUsername(@PathVariable String username);

    @GetMapping("/users/usernames")
    Flux<UserDto> findByUsername(@RequestParam("usernames") Set<String> usernames);
}
