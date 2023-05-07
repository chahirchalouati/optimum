package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
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
}
