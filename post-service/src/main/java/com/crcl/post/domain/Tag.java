package com.crcl.post.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
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