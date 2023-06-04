package com.crcl.am.configuration.security;

import java.security.KeyPair;

public abstract class JwkProvider {

    public abstract KeyPair getKeyPair();
}
