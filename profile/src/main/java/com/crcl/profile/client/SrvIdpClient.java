package com.crcl.profile.client;

import com.crcl.core.dto.RestPage;
import com.crcl.core.dto.UserDto;
import com.crcl.profile.configuration.SrvConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "${client.srvAuthentication.name}",
        url = "${client.srvAuthentication.url}",
        configuration = SrvConfiguration.class
)
public interface SrvIdpClient {

    @GetMapping("/users/username/{username}")
    UserDto findByUsername(@PathVariable String username);

    @GetMapping("/users")
    RestPage<UserDto> findAll(Pageable pageable);
}
