package com.crcl.notification.client;

import com.crcl.notification.configuration.SrvConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "configuration",
        url = "${spring.cloud.config.uri}",
        configuration = SrvConfiguration.class)
public interface ConfigClient {
}
