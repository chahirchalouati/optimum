package com.crcl.post.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {
    @OneToMany
    private Set<Like> likes = new HashSet<>();

}
