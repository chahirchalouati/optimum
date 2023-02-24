package com.crcl.post.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;;import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts", indexes = {
        @Index(name = "idx_post_username", columnList = "username")
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor()
@Accessors(chain = true)
public class Post extends Common {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    protected Set<Attachment> attachments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    protected Set<Tag> tags = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    protected Set<Tag> likes = new HashSet<>();
}
