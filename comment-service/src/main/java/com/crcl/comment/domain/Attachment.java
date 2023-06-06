package com.crcl.comment.domain;

import com.crcl.comment.converters.AdditionalDataConverter;
import com.crcl.common.domain.Orientation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@Table(name = "attachments")
public class Attachment extends BaseEntity {
    private String etag;
    private String name;
    private String bucket;
    private String version;
    private String contentType;
    private String username;
    @Getter(AccessLevel.NONE)
    @JsonIgnore
    private String link;
    @Convert(converter = AdditionalDataConverter.class)
    @Column(columnDefinition = "JSON")
    private Map<String, Object> additionalData = new HashMap<>();
    @Enumerated(EnumType.STRING)
    private Orientation orientation;

    public String getLink() {
        return this.etag.concat("/").concat(this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Attachment that = (Attachment) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
