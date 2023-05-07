package com.crcl.userInfo.client;

import com.crcl.common.dto.UserDto;
import com.crcl.userInfo.configuration.security.OAuthServerFeignConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(
        name = "${client.srvAuthentication.name}",
        url = "${client.srvAuthentication.url}",
        configuration = OAuthServerFeignConfig.class
)
public interface SrvIdpClient {
    @GetMapping("/friends/user/{username}")
    Mono<Page<UserDto>> findFriends(@PathVariable("username") String username, Pageable pageable);
}
