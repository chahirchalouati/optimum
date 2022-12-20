package com.crcl.post.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Like extends BaseEntity {
    private Boolean like;
}
