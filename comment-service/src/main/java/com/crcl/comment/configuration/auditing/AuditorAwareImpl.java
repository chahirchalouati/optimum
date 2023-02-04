package com.crcl.comment.configuration.auditing;

import com.crcl.comment.service.UserService;
import com.crcl.common.dto.UserDto;
import org.springframework.data.domain.AuditorAware;

import java.util.Objects;
import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<String> {
    private final UserService userService;

    public AuditorAwareImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        UserDto currentUser = this.userService.getCurrentUser();
        return Objects.isNull(currentUser) ? Optional.of("anonymous") : Optional.of(currentUser.getUsername());
    }
}
