package com.crcl.userInfo.client;


import com.crcl.userInfo.configuration.Oauth2.OAuthServerFeignConfig;
import com.crcl.userInfo.domain.ProfileDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@ReactiveFeignClient(
        name = "${client.profile.name}",
        url = "${client.profile.url}",
        configuration = {
                OAuthServerFeignConfig.class
        })
public interface ProfileClient {

    @GetMapping("/searches/username")
    Mono<ProfileDto> findByUsername(@RequestParam("username") String username);

    @GetMapping("/searches/usernames")
    Flux<ProfileDto> findByUsernames(@RequestParam("usernames") List<String> userNames);
}
