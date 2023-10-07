package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Education {
    private String university;
    private String highSchool;
}
