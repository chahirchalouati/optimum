package com.crcl.am.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "keyFiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyFile {
    private String id;
    private String privateKeyPath;
    private String publicKeyPath;
    private LocalDateTime creationDate;
    private boolean enabled;
    private String algorithm;
    private int keySize;
}