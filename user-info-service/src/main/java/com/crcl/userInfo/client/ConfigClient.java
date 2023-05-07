package com.crcl.userInfo.client;

import com.crcl.userInfo.configuration.security.OAuthServerFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "configuration",
        url = "${spring.cloud.config.uri}",
        configuration = OAuthServerFeignConfig.class)
public interface ConfigClient {
}
