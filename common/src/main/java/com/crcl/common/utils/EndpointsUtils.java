package com.crcl.common.utils;

public class EndpointsUtils {

    public static class Permitted {
        public static final String ACTUATOR_END_POINTS = "/actuator/**";
        public static final String[] SWAGGER_END_POINTS = {"/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**"};
    }

}
