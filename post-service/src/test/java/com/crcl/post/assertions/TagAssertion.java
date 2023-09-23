package com.crcl.post.assertions;

import com.crcl.post.domain.Tag;
import org.assertj.core.api.AbstractAssert;

public class TagAssertion extends AbstractAssert<TagAssertion, Tag> {


    protected TagAssertion(Tag tag) {
        super(tag, TagAssertion.class);
    }

    public static TagAssertion assertThat(Tag tag) {
        return new TagAssertion(tag);
    }
}
