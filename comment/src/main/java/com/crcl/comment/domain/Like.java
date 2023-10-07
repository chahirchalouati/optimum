package com.crcl.comment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = Like.ENTITY_NAME)
@Table(name = "likes")
public class Like extends BaseEntity implements Serializable {
    public static final String ENTITY_NAME = "Like";
    private static final long serialVersionUID = -652358677704324707L;
}