package com.crcl.post.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = Comment.ENTITY_NAME)
@Table(name = "comments")
public class Comment extends Common implements Serializable {
    public static final String ENTITY_NAME = "Comment";
    private static final long serialVersionUID = -6483377583531145808L;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    @ToString.Exclude
    protected Set<Attachment> attachments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    @ToString.Exclude
    protected Set<Tag> tags = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    @ToString.Exclude
    protected Set<Tag> likes = new HashSet<>();

}