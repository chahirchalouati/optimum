package com.crcl.authentication.configuration.props;

import lombok.Data;

@Data
public class Registration {
    private Integer tokenAccessTimeToLeave = 300;
    private Integer refreshTokenAccessTimeToLeave = 300;
    private String id;
    private String uri;
    private String[] scopes;
}
