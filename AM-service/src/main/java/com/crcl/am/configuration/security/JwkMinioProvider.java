package com.crcl.am.configuration.security;

import com.crcl.am.configuration.props.SecurityProperties;
import com.crcl.am.domain.KeyFile;
import com.crcl.am.repository.KeyFileRepository;
import com.crcl.common.utils.FileUtils;
import io.minio.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Profile("!dev")
@Component
@Slf4j
@RequiredArgsConstructor
public class JwkMinioProvider extends JwkProvider {
    private static final int KEY_SIZE = 2048;
    private final MinioClient minioClient;
    private final KeyFileRepository keyFileRepository;
    private final SecurityProperties securityProperties;

    @PostConstruct
    private void setup() {
        KeyFile keyFile = keyFileRepository.findFirstByEnabledOrderByCreationDateDesc(true);
        if (keyFile == null) {
            log.info("No enabled key file found. Uploading keys to Minio...");
            this.uploadKeysToMinio();
        }
    }

    public KeyPair getKeyPair() {
        try {
            log.info("Retrieving the last enabled key pair...");
            KeyFile keyFile = keyFileRepository.findFirstByEnabledOrderByCreationDateDesc(true);

            byte[] privateKeyBytes = getKeyByPath(keyFile.getPrivateKeyPath(), securityProperties.getCertificationBucket());
            byte[] publicKeyBytes = getKeyByPath(keyFile.getPublicKeyPath(), securityProperties.getCertificationBucket());

            KeyFactory keyFactory = KeyFactory.getInstance(keyFile.getAlgorithm());

            byte[] decodePrivateKey = Base64.getDecoder()
                    .decode(privateKeyBytes);
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodePrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            byte[] decodePublicKey = Base64.getDecoder()
                    .decode(publicKeyBytes);
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodePublicKey);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            log.info("Retrieved the last enabled key pair successfully.");

            return new KeyPair(publicKey, privateKey);
        } catch (Exception e) {
            log.error("Error when retrieved the last enabled key pair: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private byte[] getKeyByPath(String keyPath, String bucket) throws Exception {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(keyPath)
                        .build()).readAllBytes();
    }

    private String writePem(String type, byte[] encoded, String name) {
        try {
            String filename = "%s_%s.pem".formatted(type, name);
            try (FileWriter fileWriter = new FileWriter(filename)) {
                fileWriter.write(Base64.getEncoder().encodeToString(encoded));
            }
            log.info("Stored %s key as PEM: %s".formatted(type, filename));

            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store %s key".formatted(type), e);
        }
    }

    private void uploadToMinio(String bucketName, String objectName, String filename) {
        try {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                log.info("Created Minio bucket: %s".formatted(bucketName));
            }
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(Files.newInputStream(Paths.get(filename)), -1, 10485760)
                            .build()
            );
            log.info("Uploaded %s file to Minio: %s".formatted(objectName, filename));
        } catch (Exception e) {
            throw new RuntimeException("Error uploading %s key to Minio".formatted(objectName), e);
        }
    }

    private void uploadKeysToMinio() {
        log.info("Uploading keys to Minio...");
        KeyPair keyPair = generateKey();
        String id = UUID.randomUUID().toString();

        PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
        String privateKeyFile = writePem("PRIVATE KEY", privateSpec.getEncoded(), id);

        X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
        String publicKeyFile = writePem("PUBLIC KEY", publicSpec.getEncoded(), id);

        this.uploadToMinio(securityProperties.getCertificationBucket(), privateKeyFile, privateKeyFile);
        this.uploadToMinio(securityProperties.getCertificationBucket(), publicKeyFile, publicKeyFile);

        KeyFile keyFile = KeyFile.builder()
                .privateKeyPath(privateKeyFile)
                .publicKeyPath(publicKeyFile)
                .creationDate(LocalDateTime.now(Clock.systemDefaultZone()))
                .enabled(true)
                .algorithm("RSA")
                .keySize(2048)
                .build();

        keyFileRepository.insert(keyFile);

        log.info("Stored file paths on MongoDB");
        FileUtils.cleanUpFiles(privateKeyFile, publicKeyFile);
        log.info("Uploaded keys to Minio successfully.");
    }

    private KeyPair generateKey() {
        try {
            log.info("Generating RSA key pair...");
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE);

            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate RSA key pair", e);
        }
    }

}
