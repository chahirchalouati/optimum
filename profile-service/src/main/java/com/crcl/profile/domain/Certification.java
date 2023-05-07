package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
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
