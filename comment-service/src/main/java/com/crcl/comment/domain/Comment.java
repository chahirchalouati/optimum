package com.crcl.comment.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "post_id")
    private String postId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "content")
    private String content;
}
