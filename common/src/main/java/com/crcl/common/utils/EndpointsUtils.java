package com.crcl.common.utils;

public class EndpointsUtils {

    public interface Permitted {
        String ACTUATOR_END_POINTS = "/actuator/**";
        String[] SWAGGER_END_POINTS = {"/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**"};
    }

}
