package com.crcl.post.mapper;

import com.crcl.common.utils.generic.AuditMapper;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.AuditRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface PostAuditMapper extends AuditMapper<Post, AuditRequest> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "attachmentsSize", expression = "java(entity.getAttachments().size())"),
            @Mapping(target = "likesSize", expression = "java(entity.getLikes().size())"),
            @Mapping(target = "tagsSize", expression = "java(entity.getTags().size())"),
            @Mapping(target = "visibility", source = "visibility")
    })
    AuditRequest toAudit(Post entity);

}
