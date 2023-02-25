package com.crcl.processor.clients;


import com.crcl.common.dto.UserDto;
import com.crcl.processor.configuration.Oauth2.OAuthFeignConfig;
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
    UserDto findByUsername(@PathVariable String username);

    @GetMapping("/users/all")
    List<UserDto> findAll();
}
