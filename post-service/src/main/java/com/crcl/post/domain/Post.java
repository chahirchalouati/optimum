package com.crcl.post.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = Post.ENTITY_NAME)
@Table(name = "posts", indexes = {
        @Index(name = "idx_post_username", columnList = "username")
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor()
@Accessors(chain = true)
public class Post extends Common implements Serializable {

    public static final String ENTITY_NAME = "Post";
    @Serial
    private static final long serialVersionUID = -6484477583531145808L;

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
    protected Set<Like> likes = new HashSet<>();
}
