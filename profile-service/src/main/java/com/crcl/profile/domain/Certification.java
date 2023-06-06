package com.crcl.profile.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Certification {
    @NotBlank
    private String name;
    @PastOrPresent
    private LocalDateTime date;
    @NotBlank
    private String issuer;
}
