package com.crcl.post.repository;

import com.crcl.post.domain.Post;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CommonRepository<Post> {
    @NotNull
    @Query(" SELECT p FROM Post p WHERE p.username =?#{ principal }")
    Page<Post> findByLoggedUser(@NotNull Pageable pageable);
}
