package com.crcl.processor.clients;


import com.crcl.common.dto.UserDto;
import com.crcl.processor.configuration.Oauth2.OAuthFeignConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;


@ReactiveFeignClient(
        name = "${client.authentication.name}",
        url = "${client.authentication.url}",
        configuration = OAuthFeignConfig.class
)
public interface IdpClient {

    @GetMapping("/users/username/{username}")
    Mono<UserDto> findByUsername(@PathVariable String username);

}
