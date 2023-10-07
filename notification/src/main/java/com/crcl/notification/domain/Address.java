package com.crcl.notification.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Address {
    @Id
    private String id;
    private String number;
    private String street;
    private String city;
    private String country;
    private boolean active = true;
}
