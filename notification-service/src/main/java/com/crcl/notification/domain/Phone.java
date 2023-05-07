package com.crcl.notification.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Phone {
    @Id
    private String id;
    private PhoneType type;
    private boolean active = true;

    private enum PhoneType {
        MOBILE, PHONE
    }
}
