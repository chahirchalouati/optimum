package com.crcl.post.repository;

import com.crcl.post.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @Query("select (count(a) > 0) from Attachment a where upper(a.name) = upper(?1) and a.username =?#{ principal } ")
    boolean existsByNameIgnoreCase(String name);

    List<Attachment> findByEtag(String etag);

}