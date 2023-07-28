package com.crcl.post.mapper;

import com.crcl.core.utils.generic.GenericMapper;
import com.crcl.post.actions.ActionValidator;
import com.crcl.post.client.CommentClient;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.dto.PostDto;
import com.crcl.post.utils.CrclUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Mapper(componentModel = "spring",
        imports = {ActionValidator.class, CommentClient.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PostMapper implements GenericMapper<Post, PostDto> {

    @Autowired
    private Set<ActionValidator> validators;


    @Override
    public PostDto toDto(Post entity) {
        if (entity == null) {
            return null;
        }

        PostDto postDto = new PostDto();
        postDto.setId(entity.getId());
        postDto.setContent(entity.getContent());
        postDto.setCreator(entity.getCreator());
        postDto.setAccess(entity.getAccess());
        postDto.setCreateDate(entity.getCreateDate());
        postDto.setLastModifiedDate(entity.getLastModifiedDate());
        postDto.setLikesCount(postDto.getLikesCount());
        postDto.setDisLikesCount(postDto.getDisLikesCount());
        postDto.setShareCount(postDto.getShareCount());

        CrclUtils.applyIfNotEmpty(entity.getVideos(), postDto::setVideos);
        CrclUtils.applyIfNotEmpty(entity.getImages(), postDto::setImages);
        CrclUtils.applyIfNotEmpty(entity.getTags(), postDto::setTags);
        CrclUtils.applyIfNotEmpty(entity.getLikes(), postDto::setLikes);
        CrclUtils.applyIfNotEmpty(entity.getGenericFiles(), postDto::setGenericFiles);
        CrclUtils.applyIfNotEmpty(entity.getSharedWithUsers(), postDto::setSharedWithUsers);

        validators.forEach(actionValidator -> actionValidator.validate(postDto));

        return postDto;
    }

    @Mappings({@Mapping(target = "sharedWithUsers", ignore = true)})
    public abstract Post toEntity(CreatePostRequest request);
}
