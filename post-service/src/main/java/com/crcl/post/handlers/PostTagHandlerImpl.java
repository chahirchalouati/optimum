package com.crcl.post.handlers;

import com.crcl.post.client.IdpClient;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Tag;
import com.crcl.post.repository.TagRepository;

import java.util.List;

public class PostTagHandlerImpl extends TagHandler {
    public PostTagHandlerImpl(TagRepository tagRepository, IdpClient idpClient) {
        super(tagRepository, idpClient);
    }

    @Override
    public Tag.TagKind getKind() {
        return Tag.TagKind.POST;
    }

    @Override
    public void handle(List<Tag> tags, Post post) {
        List<Tag> tagList = getTags(tags);
        System.out.println(tagList);
    }
}
