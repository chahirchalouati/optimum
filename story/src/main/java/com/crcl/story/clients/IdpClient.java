package com.crcl.story.clients;

import com.crcl.core.dto.UserDto;
import com.crcl.story.configuration.security.OAuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "${client.authentication.name}",
        url = "${client.authentication.url}",
        configuration = OAuthFeignConfig.class
)
public interface IdpClient {

    @GetMapping("/users/username/{username}")
    UserDto findByUsername(@PathVariable("username") String username);

    @GetMapping("/users/all")
    List<UserDto> findAll();
}
