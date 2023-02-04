package com.crcl.comment.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
public class Comment extends BaseEntity {

    @Column(name = "post_id")
    private String postId;

    @Column(name = "content")
    @Lob
    private String content;
}
