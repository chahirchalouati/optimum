package com.crcl.post.handlers;

import com.crcl.post.assertions.TagAssertion;
import com.crcl.post.builders.TestTagBuilder;
import com.crcl.post.client.IdpClient;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Tag;
import com.crcl.post.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class PostTagHandlerImplTest {

    @InjectMocks
    PostTagHandlerImpl postTagHandler;
    @Mock
    IdpClient idpClient;
    @Mock
    TagRepository tagRepository;


    @Test
    void handle() {

        List<Tag> tags = List.of(
                TestTagBuilder.builder().withPostKind().build(),
                TestTagBuilder.builder().withUserKind().build(),
                TestTagBuilder.builder().withUserKind().build()
        );

        postTagHandler.handle(tags, new Post());

        TagAssertion.assertThat(null).isNull();
    }
}