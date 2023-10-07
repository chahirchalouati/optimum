package com.crcl.post.handlers;

import com.crcl.core.domain.TagKind;
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
    public TagKind getKind() {
        return TagKind.POST;
    }

    @Override
    public void handle(List<Tag> tags, Post post) {
        List<Tag> tagList = getTags(tags);
        System.out.println(tagList);
    }
}
