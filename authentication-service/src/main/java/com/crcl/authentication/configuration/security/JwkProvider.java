package com.crcl.authentication.configuration.security;

import com.crcl.authentication.domain.KeyFile;
import com.crcl.authentication.repository.KeyFileRepository;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwkProvider {
    private static final int KEY_SIZE = 2048;

    private final MinioClient minioClient;
    private final KeyFileRepository keyFileRepository;

    public KeyPair generateKeyPair() {
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate RSA key pair", e);
        }
    }

    @PostConstruct
    public void setup() throws IOException {
        KeyFile keyFile = keyFileRepository.findFirstByEnabledOrderByCreationDateDesc(true);
        if (keyFile == null) {
            this.uploadKeysToMinio();
        }

    }

    public void uploadKeysToMinio() throws IOException {
        final var keyPair = generateKeyPair();
        final var id = UUID.randomUUID().toString();
        final var privateSpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
        final var publicSpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
        final var privateKeyFile = writePem("PRIVATE KEY", privateSpec.getEncoded(), id);
        final var publicKeyFile = writePem("PUBLIC KEY", publicSpec.getEncoded(), id);

        this.uploadToMinio("key-private-cert", privateKeyFile, privateKeyFile);
        this.uploadToMinio("key-public-cert", publicKeyFile, publicKeyFile);

        // Save file paths to MongoDB
        var keyFile = KeyFile.builder()
                .privateKeyPath(privateKeyFile)
                .publicKeyPath(publicKeyFile)
                .creationDate(LocalDateTime.now(Clock.systemDefaultZone()))
                .enabled(true)
                .algorithm("RSA")
                .keySize(2048)
                .build();

        keyFileRepository.insert(keyFile);
        log.info("Stored file paths on MongoDB");
        // Clean up files
        Files.delete(Paths.get(privateKeyFile));
        Files.delete(Paths.get(publicKeyFile));

    }

    public KeyPair getLastEnabledKeyPair() throws Exception {
        // Get the last enabled key file from MongoDB
        final var keyFile = keyFileRepository.findFirstByEnabledOrderByCreationDateDesc(true);

        // Read the contents of the private key and public key files from Minio
        final byte[] privateKeyBytes = getKeyByPath(keyFile.getPrivateKeyPath(), "key-private-cert");
        final byte[] publicKeyBytes = getKeyByPath(keyFile.getPublicKeyPath(), "key-public-cert");

        // Convert the key file contents to a KeyPair object
        final var privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyBytes));
        final var publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyBytes));
        final var keyFactory = KeyFactory.getInstance(keyFile.getAlgorithm());
        final var privateKey = keyFactory.generatePrivate(privateKeySpec);
        final var publicKey = keyFactory.generatePublic(publicKeySpec);

        return new KeyPair(publicKey, privateKey);
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
            String filename = type + "_" + name + ".pem";
            try (FileWriter fileWriter = new FileWriter(filename)) {
                fileWriter.write(Base64.getEncoder().encodeToString(encoded));
            }
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store " + type + " key", e);
        }
    }

    private void uploadToMinio(String bucketName, String objectName, String filename) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(Files.newInputStream(Paths.get(filename)), -1, 10485760)
                            .build()
            );
            log.info("Uploaded " + objectName + " file to Minio: " + filename);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading " + objectName + " key to Minio", e);
        }
    }

}
