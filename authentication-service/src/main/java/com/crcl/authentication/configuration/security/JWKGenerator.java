package com.crcl.authentication.configuration.security;

import com.crcl.authentication.domain.Key;
import com.crcl.authentication.repository.RSAKeyRepository;
import com.github.dockerjava.api.exception.InternalServerErrorException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;

import javax.annotation.PostConstruct;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.UUID;

@Configuration
public class JWKGenerator {
    @Autowired
    private RSAKeyRepository rsaKeyRepository;

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    @PostConstruct
    private void generateRsa() {
        var one = rsaKeyRepository.findOne(Example.of(new Key().setValid(true)));
        if (one.isEmpty()) {
            KeyPair keyPair = generateRsaKey();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSAKey rsaKey = new RSAKey.Builder(publicKey)
                    .privateKey(privateKey)
                    .keyID(UUID.randomUUID()
                            .toString()).build();
            rsaKeyRepository.save(new Key().setValid(true).setValue(rsaKey.toJSONObject()));

        }
    }

    @Bean
    @SneakyThrows
    public JWKSource<SecurityContext> jwkSource() {
        var rsaKey = rsaKeyRepository.findOne(Example.of(new Key().setValid(true)))
                .map(key -> {
                    try {
                        return RSAKey.parse(key.getValue());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new InternalServerErrorException(""));
        return (jwkSelector, securityContext) -> jwkSelector.select(new JWKSet(rsaKey));
    }

}
