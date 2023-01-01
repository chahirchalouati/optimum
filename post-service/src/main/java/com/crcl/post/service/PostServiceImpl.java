package com.crcl.post.service;

import com.crcl.post.domain.Post;
import com.crcl.post.dto.PostDto;
import com.crcl.post.mapper.PostMapper;
import com.crcl.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

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
}
