package com.crcl.post.client;

import com.crcl.post.configuration.FeignFormConfig;
import com.crcl.post.configuration.OAuthFeignConfig;
import com.crcl.post.dto.ProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// TODO: 1/8/2023 add resiliency
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
