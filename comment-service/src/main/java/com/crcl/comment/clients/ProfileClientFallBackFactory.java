package com.crcl.comment.clients;

import com.crcl.comment.dto.ProfileDto;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.List;
import java.util.Set;


public class ProfileClientFallBackFactory implements FallbackFactory<ProfileClient> {
    @Override
    public ProfileClient create(Throwable cause) {
        return new ProfileClient() {
            @Override
            public ProfileDto findByUsername(String username) {
                System.out.println("findbyUsername is called");
                return new ProfileDto();
            }

            @Override
            public List<ProfileDto> findByUsernames(Set<String> userNames) {
                System.out.println("find by usernames is called");
                return List.of();
            }
        };
    }
}
