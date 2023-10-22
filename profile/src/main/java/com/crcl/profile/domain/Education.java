package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Education extends Location {
    private String university;
    private String highSchool;
}
