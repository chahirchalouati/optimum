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
@Entity(name = Tag.ENTITY_NAME)
@Table(name = "tags")
public class Tag extends BaseEntity implements Serializable {
    public static final String ENTITY_NAME = "Tag";
    private static final long serialVersionUID = 4917545087208701084L;


}