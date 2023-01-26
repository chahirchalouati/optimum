package com.crcl.post.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Post posts;

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

    public String getLink() {
        return this.etag.concat("/").concat(this.name);
    }
}
