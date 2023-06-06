package com.crcl.profile.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class WorkExperience {
    @NotBlank
    private String title;
    @NotBlank
    private String employerName;
    @PastOrPresent
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Location location;

}
