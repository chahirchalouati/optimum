package com.crcl.notification.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
@Data
@Accessors(chain = true)
public class Template {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotBlank(message = "Name: cannot be blank")
    private String name;
    @NotBlank(message = "Content: cannot be blank")
    private String content;
    @NotBlank(message = "Style: cannot be blank")
    private String style;
}
