package com.crcl.authentication.clients;

import com.crcl.authentication.configuration.security.OAuthFeignConfig;
import com.crcl.authentication.dto.ProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${client.profile.name}", url = "${client.profile.url}", configuration = {OAuthFeignConfig.class})
public interface SrvProfileClient {
    @GetMapping("/profiles/profile/username/{username}")
    ProfileDto findByUsername(@PathVariable String username);
}
