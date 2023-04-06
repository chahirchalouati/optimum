package com.crcl.notification.client;


import com.crcl.common.dto.UserDto;
import com.crcl.notification.configuration.Oauth2.OAuthFeignConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @GetMapping("/friends/user/{username}")
    Mono<Page<UserDto>> findFriends(@PathVariable("username") String username, Pageable pageable);
}
