package com.crcl.post.service;

import com.crcl.post.actionValidators.*;
import com.crcl.post.domain.Access;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.PostDto;
import com.crcl.post.mapper.PostMapper;
import com.crcl.post.repository.PostRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PostServiceImplTest {

    @InjectMocks
    private PostServiceImpl postService;
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserService userService;
    @Mock
    private PostMapper postMapper;

    @Mock
    private Set<ActionValidator> validators;

    @Before
    public void setup() {
        doNothing().when(validators).forEach(any());
    }

    @Test
    public void test() {
        Post post = new Post()
                .setContent("bla bla")
                .setAccess(Access.PUBLIC);
        when(postRepository.findAll()).thenReturn(List.of(post));
        when(postMapper.toDto(any(Post.class))).thenReturn(new PostDto().setContent(""));

        List<PostDto> postDtos = postService.findAll();
        postDtos.forEach(Assert::assertNotNull);
    }

}
























