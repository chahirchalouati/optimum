package com.crcl.authenticationservice.configuration.props;

import lombok.Data;

@Data
public class ClientsProps {
    private Integer tokenAccessTimeToLeave = 300;
    private Integer refreshTokenAccessTimeToLeave = 300;
}
