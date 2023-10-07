package com.crcl.post.mapper;

import com.crcl.core.utils.generic.GenericMapper;
import com.crcl.post.actions.ActionValidator;
import com.crcl.post.client.CommentClient;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.dto.PostDto;
import com.crcl.post.service.ShareService;
import com.crcl.post.service.TagService;
import com.crcl.post.utils.CrclUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = {
                ActionValidator.class,
                CommentClient.class,
                TagService.class,
                ShareService.class
        })
public abstract class PostMapper implements GenericMapper<Post, PostDto> {

    @Autowired
    private Set<ActionValidator> validators;
    @Autowired
    private TagService tagService;
    @Autowired
    private ShareService shareService;

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
        postDto.setPublishState(entity.getPublishState());

        CrclUtils.applyIfNotEmpty(entity.getVideos(), postDto::setVideos);
        CrclUtils.applyIfNotEmpty(entity.getImages(), postDto::setImages);
        CrclUtils.applyIfNotEmpty(tagService.findByEntityId(entity.getId()), postDto::setTags);
        CrclUtils.applyIfNotEmpty(entity.getGenericFiles(), postDto::setGenericFiles);

        validators.forEach(actionValidator -> actionValidator.validate(postDto));

        return postDto;
    }

    public abstract Post toEntity(CreatePostRequest request);
}
