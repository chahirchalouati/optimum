package com.crcl.post.fallbacks;

import com.crcl.common.dto.ProfileDto;
import com.crcl.post.client.ProfileClient;
import com.github.dockerjava.api.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProfileClientFallbackFactory implements FallbackFactory<ProfileClient> {

    @Override
    public ProfileClient create(Throwable cause) {
        return new ProfileClient() {
            @Override
            public ProfileDto findByUsername(String username) {
                log.error("Fallback due to: " + cause.getMessage());
                throw new BadRequestException(NestedExceptionUtils.getMostSpecificCause(cause));
            }

            @Override
            public List<ProfileDto> findByUsernames(List<String> userNames) {
                log.error("Fallback due to: " + cause.getMessage());
                throw new BadRequestException(NestedExceptionUtils.getMostSpecificCause(cause));
            }
        };
    }
}
