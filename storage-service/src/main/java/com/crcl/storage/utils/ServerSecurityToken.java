package com.crcl.storage.utils;

public class ServerSecurityToken {
    private static String token;

    public static String setToken(String value) {
        token = value;
        return token;
    }

    public static String getToken() {
        return token;
    }

}
