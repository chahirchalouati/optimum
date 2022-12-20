package com.crcl.notification.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Mail {
    @Id
    private String id;
    private String mail;
    private Type type;
    private String password;
    private boolean active = true;
    private boolean system = false;

}
