package com.crcl.profile.client;

import com.crcl.profile.configuration.OAuthFeignConfig;
import com.crcl.profile.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "${client.srvAuthentication.name}",
        url = "${client.authentication.url}",
        configuration = OAuthFeignConfig.class
)
public interface SrvIdpClient {

    @GetMapping("/users/all")
    List<UserDto> findAll();
}
