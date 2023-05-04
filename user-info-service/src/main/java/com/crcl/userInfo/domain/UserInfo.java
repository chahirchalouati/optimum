package com.crcl.userInfo.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserInfo {
    @Id
    private String userId;
    private UserInfo userInfo;
}
