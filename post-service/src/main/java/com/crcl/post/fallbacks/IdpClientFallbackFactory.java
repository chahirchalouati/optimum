package com.crcl.post.fallbacks;

import com.crcl.core.dto.UserDto;
import com.crcl.post.client.IdpClient;
import com.github.dockerjava.api.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class IdpClientFallbackFactory implements FallbackFactory<IdpClient> {
    @Override
    public IdpClient create(Throwable cause) {
        return new IdpClient() {
            @Override
            public UserDto findByUsername(String username) {
                log.error("Fallback due to: " + cause.getMessage());
                throw new BadRequestException(NestedExceptionUtils.getMostSpecificCause(cause));
            }

            @Override
            public List<UserDto> findByUsername(Set<String> usernames) {
                log.error("Fallback due to: " + cause.getMessage());
                throw new BadRequestException(NestedExceptionUtils.getMostSpecificCause(cause));
            }
        };
    }
}
