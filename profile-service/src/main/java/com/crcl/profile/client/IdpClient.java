package com.crcl.profile.client;

import com.crcl.profile.configuration.OAuthFeignConfig;
import com.crcl.profile.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "${client.authentication.name}", url = "${client.authentication.url}", configuration = OAuthFeignConfig.class)
public interface IdpClient {

    @GetMapping("/users/username/{username}")
    UserDto findByUsername(@PathVariable String username);

    @GetMapping("/users/all")
    List<UserDto> findAll();
}
