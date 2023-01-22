package com.crcl.post.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
public class Post extends BaseEntity {
    private String username;
    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private Visibility visibility = Visibility.PRIVATE;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    private Set<Attachment> attachments = new HashSet<>();

    public enum Visibility {
        PUBLIC, FRIEND, PRIVATE
    }
}
