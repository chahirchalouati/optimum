package com.crcl.post.mapper;

import com.crcl.common.utils.generic.GenericMapper;
import com.crcl.post.actionValidators.ActionValidator;
import com.crcl.post.client.CommentClient;
import com.crcl.post.domain.*;
import com.crcl.post.dto.PostDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.Set;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PostMapper implements GenericMapper<Post, PostDto> {
    @Autowired
    private Set<ActionValidator> validators;
    @Autowired
    private CommentClient commentClient;

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
        Set<Video> videos = entity.getVideos();
        if (videos != null) {
            postDto.setVideos(new LinkedHashSet<>(videos));
        }
        Set<Image> images = entity.getImages();
        if (images != null) {
            postDto.setImages(new LinkedHashSet<>(images));
        }
        Set<Tag> tags = entity.getTags();
        if (tags != null) {
            postDto.setTags(new LinkedHashSet<>(tags));
        }
        Set<Like> likes = entity.getLikes();
        if (tags != null) {
            postDto.setLikes(new LinkedHashSet<>(likes));
        }
        postDto.setLikesCount(postDto.getLikes().size());
        postDto.setShareCount(postDto.getLikes().size());
        postDto.setCommentCount(commentClient.countByPost(entity.getId()));
        validators.forEach(actionValidator -> actionValidator.validate(postDto));
        return postDto;
    }
}
