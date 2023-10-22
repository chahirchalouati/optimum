package com.crcl.profile.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class VolunteerExperience {
    @NotBlank
    private String organization;
    @NotBlank
    private String role;
    @PastOrPresent
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Location location;
}
