package com.crcl.post.service.impl;

import com.crcl.post.clients.ProfileClient;
import com.crcl.post.clients.StorageClient;
import com.crcl.post.domain.Attachment;
import com.crcl.post.domain.Post;
import com.crcl.post.dto.FileUploadResponse;
import com.crcl.post.dto.PostDto;
import com.crcl.post.dto.PostFormDto;
import com.crcl.post.dto.ProfileDto;
import com.crcl.post.exceptions.DuplicateFileNameException;
import com.crcl.post.mapper.PostMapper;
import com.crcl.post.repository.AttachmentRepository;
import com.crcl.post.repository.PostRepository;
import com.crcl.post.service.PostService;
import com.crcl.post.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final StorageClient storageClient;
    private final UserService userService;
    private final ProfileClient profileClient;
    private final AttachmentRepository attachmentRepository;

    public PostDto save(PostDto postDto) {
        Post user = this.postMapper.toEntity(postDto);
        return postMapper.toDto(postRepository.save(user));
    }

    @Override
    public List<PostDto> saveAll(List<PostDto> entities) {
        return entities.stream()
                .map(this::save)
                .toList();
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
        if (!pageable.getSort().isSorted()) {
            pageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.Direction.DESC,
                    "createdAt");
        }

        final Page<Post> posts = postRepository.findByLoggedUser(pageable);
        final Set<String> usersNames = posts.getContent().stream()
                .map(Post::getUsername)
                .collect(toSet());
        List<ProfileDto> profiles = this.profileClient.findByUsernames(usersNames);
        Map<String, ProfileDto> profileMap = profiles.stream()
                .collect(Collectors.toMap(p -> p.getUser().getUsername(), p -> p));
        return posts
                .map(postMapper::toDto)
                .map(mergePostsWithOwnerProfiles(profileMap));
    }

    @Override
    public PostDto update(PostDto postDto, Long id) {
        return postRepository.findById(id)
                .map(user -> postMapper.toEntity(postDto))
                .map(postRepository::save)
                .map(postMapper::toDto)
                .orElse(null);
    }

    @Override
    public PostDto save(PostFormDto postFormDto) {
        final var post = new Post();

        if (!CollectionUtils.isEmpty(postFormDto.getFiles())) {
            final var files = List.copyOf(postFormDto.getFiles());
            validateFilesName(files);
            final var responses = this.storageClient.saveAll(files);
            post.setAttachments(getAttachments(responses));
        }

        post.setContent(postFormDto.getContent());
        post.setVisibility(postFormDto.getVisibility());
        post.setUsername(userService.getCurrentUser().getUsername());
        post.setUser(userService.getCurrentUser());
        ProfileDto profileDto = profileClient.findByUsername(userService.getCurrentUser().getUsername());
        post.setProfile(profileDto);
        final Post save = postRepository.save(post);

        return postMapper.toDto(save);
    }

    private void validateFilesName(List<MultipartFile> files) {
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            log.debug("Validating file name: {}", fileName);
            boolean fileExists = attachmentRepository.existsByNameIgnoreCase(fileName);
            if (fileExists) {
                log.warn("Duplicate file name found: {}", fileName);
                throw new DuplicateFileNameException("Duplicate file name found: " + fileName);
            }
            log.debug("File name is unique: {}", fileName);
        }
        log.info("All file names are valid");
    }


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
                .collect(toSet());
    }

    private Function<PostDto, PostDto> mergePostsWithOwnerProfiles(Map<String, ProfileDto> profileMap) {
        return postDto -> {
            String postUsername = postDto.getUsername();
            ProfileDto ownerProfile = profileMap.get(postUsername);
            postDto.setOwner(ownerProfile);
            return postDto;
        };
    }


}
