package com.crcl.authentication.configuration.props;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Registration {
    private Integer tokenAccessTimeToLeave = 300;
    private Integer refreshTokenAccessTimeToLeave = 300;
    private String id;
    private List<String> uris;
    private List<String> scopes = new ArrayList<>();
    private List<String> grantTypes = new ArrayList<>();
}
