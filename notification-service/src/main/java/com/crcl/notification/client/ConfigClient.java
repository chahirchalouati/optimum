package com.crcl.notification.client;

import com.crcl.notification.configuration.Oauth2.OAuthServerFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "configuration",
        url = "${spring.cloud.config.uri}",
        configuration = OAuthServerFeignConfig.class)
public interface ConfigClient {
}
