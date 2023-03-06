package com.crcl.profile.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
