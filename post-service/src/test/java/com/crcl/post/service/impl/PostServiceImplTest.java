package com.crcl.post.service.impl;

import com.crcl.common.dto.ProfileDto;
import com.crcl.common.dto.UserDto;
import com.crcl.post.client.IdpClient;
import com.crcl.post.client.ProfileClient;
import com.crcl.post.client.StorageClient;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.dto.PostDto;
import com.crcl.post.mapper.PostMapper;
import com.crcl.post.repository.PostRepository;
import com.crcl.post.repository.TagRepository;
import com.crcl.post.service.PostQueueService;
import com.crcl.post.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @InjectMocks
    PostServiceImpl postService;
    @Mock
    PostRepository postRepository;
    @Mock
    PostMapper postMapper;
    @Mock
    StorageClient storageClient;
    @Mock
    IdpClient idpClient;
    @Mock
    TagRepository tagRepository;
    @Mock
    PostQueueService queueService;
    @Mock
    UserService userService;
    @Mock
    ProfileClient profileClient;

    @Test
    void save() {
        CreatePostRequest request = new CreatePostRequest();

        UserDto user = new UserDto()
                .setUsername("userName");

        ProfileDto userProfile = new ProfileDto()
                .setUsername(user.getUsername());

        when(userService.getCurrentUser()).thenReturn(user);
        when(postMapper.toEntity(request)).thenReturn(new Post());
        when(postMapper.toDto(new Post())).thenReturn(new PostDto());
        when(profileClient.findByUsername(eq("userName"))).thenReturn(userProfile);

        PostDto result = postService.save(request);

        Assertions.assertNotNull(result);
    }


}