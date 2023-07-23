package com.crcl.post.client;

import com.crcl.post.configuration.security.SrvConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "${client.srvUtilities.name}",
        url = "${client.srvUtilities.url}",
        configuration = SrvConfiguration.class
)
public interface SrvUtilitiesClient {
}
