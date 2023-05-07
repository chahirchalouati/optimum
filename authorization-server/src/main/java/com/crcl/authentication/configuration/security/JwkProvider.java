package com.crcl.authentication.configuration.security;

import java.security.KeyPair;

public abstract class JwkProvider {

    public abstract KeyPair getKeyPair();
}
