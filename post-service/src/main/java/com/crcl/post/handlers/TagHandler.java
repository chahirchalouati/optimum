package com.crcl.post.handlers;

import com.crcl.post.client.IdpClient;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Tag;
import com.crcl.post.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public abstract class TagHandler {

    protected final TagRepository tagRepository;
    protected final IdpClient idpClient;

    public List<Tag> getTags(List<Tag> tags) {
        return tags.stream()
                .filter(tag -> tag.getKind().equals(getKind()))
                .toList();
    }

    public abstract Tag.TagKind getKind();

    public abstract void handle(final List<Tag> tags, final Post post);

}
