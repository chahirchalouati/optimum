package com.crcl.profile.client;

import com.crcl.profile.configuration.SrvConfiguration;
import com.crcl.profile.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "${client.srvAuthentication.name}",
        url = "${client.srvAuthentication.url}",
        configuration = SrvConfiguration.class
)
public interface SrvIdpClient {

    @GetMapping("/users/username/{username}")
    UserDto findByUsername(@PathVariable String username);

    @GetMapping("/users/all")
    List<UserDto> findAll();
}
