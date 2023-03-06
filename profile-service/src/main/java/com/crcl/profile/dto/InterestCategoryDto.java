package com.crcl.profile.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InterestCategoryDto {
    private String id;
    private String name;
    private String description;
}
