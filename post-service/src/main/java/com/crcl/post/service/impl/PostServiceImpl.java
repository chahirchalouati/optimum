package com.crcl.post.service.impl;

import com.crcl.post.client.StorageClient;
import com.crcl.post.domain.Attachment;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.FileUploadResponse;
import com.crcl.post.dto.PostDto;
import com.crcl.post.dto.PostFormDto;
import com.crcl.post.mapper.PostMapper;
import com.crcl.post.repository.PostRepository;
import com.crcl.post.service.PostService;
import com.crcl.post.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final StorageClient storageClient;
    private final UserService userService;

    @Override
    public PostDto save(PostDto userDto) {
        Post user = this.postMapper.toEntity(userDto);
        return postMapper.toDto(postRepository.save(user));
    }

    @Override
    public List<PostDto> save(List<PostDto> entities) {
        return entities.stream().map(this::save).toList();
    }

    @Override
    public void deleteById(Long id) {
        postRepository.findById(id).ifPresent(user -> {
            postRepository.save(user);
            log.info("user with id %s was disabled".formatted(user.getId()));
        });
    }

    @Override
    public PostDto findById(Long id) {
        return postRepository.findById(id)
                .map(postMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<PostDto> findAll() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Override
    public Page<PostDto> findAll(Pageable pageable) {
        return postRepository.findByLoggedUser(pageable)
                .map(postMapper::toDto);
    }

    @Override
    public PostDto update(PostDto userDto, Long id) {
        return postRepository.findById(id)
                .map(user -> postMapper.toEntity(userDto))
                .map(postRepository::save)
                .map(postMapper::toDto)
                .orElse(null);
    }

    @Override
    public PostDto save(PostFormDto postFormDto) {
        final List<MultipartFile> files = postFormDto.getFiles().stream().toList();
        final List<FileUploadResponse> responses = this.storageClient.saveAll(files);
        final Post post = new Post()
                .setAttachments(getAttachments(responses))
                .setContent(postFormDto.getContent())
                .setUsername(userService.getCurrentUser().getUsername());
        final Post save = postRepository.save(post);
        return postMapper.toDto(save);
    }

    @NotNull
    private Set<Attachment> getAttachments(List<FileUploadResponse> responses) {
        return responses.stream()
                .map(response -> new Attachment()
                        .setUsername(userService.getCurrentUser().getUsername())
                        .setContentType(response.getContentType())
                        .setName(response.getName())
                        .setLink(response.getLink())
                        .setBucket(response.getBucket())
                        .setEtag(response.getEtag())
                        .setVersion(response.getVersion()))
                .collect(Collectors.toSet());
    }
}
