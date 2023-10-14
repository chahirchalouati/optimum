package com.crcl.friend_ship.clients;

import com.crcl.friend_ship.configuration.security.OAuthFeignConfig;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;


@ReactiveFeignClient(
        name = "${client.storage.name}",
        url = "${client.storage.url}",
        configuration = {OAuthFeignConfig.class})
public interface StorageClient {

    @GetMapping("/files/{folder}/{fileName}")
    Mono<ByteArrayResource> getObject(@PathVariable("fileName") String fileName, @PathVariable("folder") String folder);
}
