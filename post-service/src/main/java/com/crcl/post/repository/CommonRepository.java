package com.crcl.post.repository;

import com.crcl.post.domain.Common;
import com.crcl.post.domain.Post;
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
    @Query(" SELECT p FROM Post p WHERE p.username =?#{ principal }")
    Page<Post> findByLoggedUser(@NotNull Pageable pageable);

}