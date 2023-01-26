package com.crcl.notification.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class Credential {
    @Id
    private String id;
    private List<Mail> mails;
    private List<Address> addresses;
    private List<Phone> phones = new ArrayList<>();

}
