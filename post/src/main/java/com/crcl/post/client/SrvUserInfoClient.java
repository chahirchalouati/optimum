package com.crcl.post.client;

import com.crcl.core.dto.UserInfoDto;
import com.crcl.post.configuration.security.SrvConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "${client.srvUserInfo.name}",
        url = "${client.srvUserInfo.url}",
        configuration = SrvConfiguration.class
)
public interface SrvUserInfoClient {
    UserInfoDto findById(@PathVariable String userId);
}
