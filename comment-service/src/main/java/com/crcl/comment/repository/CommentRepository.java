package com.crcl.comment.repository;

import com.crcl.comment.domain.Comment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CommonRepository<Comment>, JpaSpecificationExecutor<Comment> {

    Page<Comment> findByPostId(Long postId, Pageable pageable);

    @NotNull
    @Query(" SELECT p FROM Comment p WHERE p.username =?#{ principal }")
    Page<Comment> findByLoggedUser(@NotNull Pageable pageable);
}
