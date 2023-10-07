package com.crcl.comment.repository;

import com.crcl.comment.domain.Comment;
import com.crcl.core.dto.EntityCountDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CommonRepository<Comment>, JpaSpecificationExecutor<Comment> {

    Page<Comment> findByPostId(String postId, Pageable pageable);

    @NotNull
    @Query(" SELECT c FROM Comment c WHERE c.username =?#{ principal }")
    Page<Comment> findByLoggedUser(@NotNull Pageable pageable);

    Integer countByPostId(String postId);

    @Query(value = " SELECT NEW com.crcl.core.dto.EntityCountDto(c.postId, COUNT(c.id)) FROM Comment c WHERE c.postId IN (?1) GROUP BY c.postId")
    List<EntityCountDto> getCounts(List<String> postsIds, Sort sort);

}
