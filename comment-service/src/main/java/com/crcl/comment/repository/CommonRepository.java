package com.crcl.comment.repository;

import com.crcl.comment.domain.Common;
import com.crcl.comment.domain.Comment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<T extends Common> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
    @NotNull
    @Query(" SELECT p FROM Comment p WHERE p.username =?#{ principal }")
    Page<Comment> findByLoggedUser(@NotNull Pageable pageable);

}