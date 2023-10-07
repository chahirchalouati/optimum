package com.crcl.post.builders;

import com.crcl.core.domain.TagKind;
import com.crcl.post.domain.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TestTagBuilder {
    private final Tag tag;

    public static TestTagBuilder builder() {
        return new TestTagBuilder(new Tag());
    }

    public Tag build() {
        return tag;
    }

    public TestTagBuilder withUserKind() {
        this.tag.setKind(TagKind.USER);
        return this;
    }

    public TestTagBuilder withPostKind() {
        this.tag.setKind(TagKind.POST);
        return this;
    }
}
