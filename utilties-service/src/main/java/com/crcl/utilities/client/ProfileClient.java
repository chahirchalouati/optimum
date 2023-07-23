package com.crcl.utilities.client;

import com.crcl.common.dto.ProfileDto;
import com.crcl.utilities.configuration.security.FeignFormConfig;
import com.crcl.utilities.configuration.security.OAuthFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(
        name = "${client.profile.name}",
        url = "${client.profile.url}",
        configuration = {
                OAuthFeignConfig.class,
                FeignFormConfig.class
        })
public interface ProfileClient {
    @GetMapping("/searches/username")
    ProfileDto findByUsername(@RequestParam("username") String username);

    @GetMapping("/searches/usernames")
    List<ProfileDto> findByUsernames(@RequestParam("usernames") List<String> userNames);
}
