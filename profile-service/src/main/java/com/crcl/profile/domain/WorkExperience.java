package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
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
