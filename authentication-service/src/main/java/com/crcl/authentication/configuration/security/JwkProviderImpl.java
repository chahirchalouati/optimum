package com.crcl.authentication.configuration.security;

import com.crcl.common.helper.FileHelper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Profile("dev")
@Component
@AllArgsConstructor
@Slf4j
@Import(FileHelper.class)
public class JwkProviderImpl extends JwkProvider {
    private final FileHelper fileHelper;

    @Override
    @SneakyThrows
    public KeyPair getKeyPair() {

        byte[] privateKeyBytes = fileHelper.loadFileAsBytes("/cert/private.pem");
        byte[] publicKeyBytes = fileHelper.loadFileAsBytes("/cert/public.pem");

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyBytes));
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyBytes));
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        log.info("Retrieved the last enabled key pair successfully.");

        return new KeyPair(publicKey, privateKey);
    }
}
