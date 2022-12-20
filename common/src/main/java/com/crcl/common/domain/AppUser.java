package com.crcl.common.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppUser {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private List<Address> addresses = new ArrayList<>();
    private List<Phone> phones = new ArrayList<>();

    @Data
    public static class Address {
        private String street;
        private String city;
        private String buildingNumber;
        private String homeNumber;
        private String code;
    }

    @Data
    public static class Phone {
        private String value;
        private Kind kind;

        public enum Kind {
            MOBILE, TELEPHONE
        }
    }
}
