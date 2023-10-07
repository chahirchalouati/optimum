package com.crcl.post.mapper;

import com.crcl.post.actions.ActionValidator;
import com.crcl.post.client.CommentClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class PostMapperTest {
    @InjectMocks
    PostMapperImpl postMapper;
    @Mock
    CommentClient commentClient;
    @Mock
    Set<ActionValidator> validators;

    @BeforeEach
    void init() {

        Assertions.assertNotNull(postMapper);
        Assertions.assertNotNull(commentClient);
        Assertions.assertNotNull(validators);
    }

    @Test
    void toDto() {
    }

    @Test
    void toEntity() {
    }
}