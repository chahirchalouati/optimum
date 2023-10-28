package com.crcl.post.client;

import com.crcl.core.dto.ProfileDto;
import com.crcl.core.utils.NotificationDefinition;
import com.crcl.core.utils.NotificationTargets;
import com.crcl.post.configuration.security.FeignFormConfig;
import com.crcl.post.configuration.security.OAuthFeignConfig;
import com.crcl.post.fallbacks.ProfileClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(
        name = "${client.profile.name}",
        url = "${client.profile.url}",
        fallbackFactory = ProfileClientFallbackFactory.class,
        configuration = {
                OAuthFeignConfig.class,
                FeignFormConfig.class
        })
public interface ProfileClient {

    @GetMapping("/searches/username")
    ProfileDto findByUsername(@RequestParam("username") String username);

    @GetMapping("/searches/usernames")
    List<ProfileDto> findByUsernames(@RequestParam("usernames") List<String> userNames);

    @GetMapping("/profiles/notifications/{username}/target")
    NotificationTargets getNotificationTarget(@PathVariable("username") String username, @RequestParam("notificationDefinition") NotificationDefinition notificationDefinition);
}
