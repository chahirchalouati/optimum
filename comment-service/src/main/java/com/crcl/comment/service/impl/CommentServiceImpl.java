package com.crcl.comment.service.impl;

import com.crcl.comment.clients.ProfileClient;
import com.crcl.comment.clients.StorageClient;
import com.crcl.comment.domain.Attachment;
import com.crcl.comment.domain.Comment;
import com.crcl.comment.dto.CommentDto;
import com.crcl.comment.dto.CommentFormDto;
import com.crcl.comment.dto.FileUploadResponse;
import com.crcl.comment.dto.ProfileDto;
import com.crcl.comment.exceptions.DuplicateFileNameException;
import com.crcl.comment.mapper.CommentMapper;
import com.crcl.comment.repository.AttachmentRepository;
import com.crcl.comment.repository.CommentRepository;
import com.crcl.comment.service.CommentService;
import com.crcl.comment.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final StorageClient storageClient;
    private final UserService userService;
    private final ProfileClient profileClient;
    private final AttachmentRepository attachmentRepository;

    @Override
    public CommentDto save(CommentDto postDto) {
        Comment user = this.commentMapper.toEntity(postDto);
        return commentMapper.toDto(commentRepository.save(user));
    }

    @Override
    public List<CommentDto> saveAll(List<CommentDto> entities) {
        return entities.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.findById(id).ifPresent(user -> {
            commentRepository.save(user);
            log.info("user with id %s was disabled".formatted(user.getId()));
        });
    }

    @Override
    public CommentDto findById(Long id) {
        return commentRepository.findById(id)
                .map(commentMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<CommentDto> findAll() {
        return commentRepository.findAll().stream()
                .map(commentMapper::toDto)
                .toList();
    }

    @Override
    public Page<CommentDto> findAll(Pageable pageable) {
        if (!pageable.getSort().isSorted()) {
            pageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.Direction.DESC,
                    "createdAt");
        }

        final Page<Comment> posts = commentRepository.findByLoggedUser(pageable);
        final Set<String> usersNames = posts.getContent().stream()
                .map(Comment::getUsername)
                .collect(toSet());
        List<ProfileDto> profiles = this.profileClient.findByUsernames(usersNames);
        Map<String, ProfileDto> profileMap = profiles.stream()
                .collect(Collectors.toMap(p -> p.getUser().getUsername(), p -> p));
        return posts
                .map(commentMapper::toDto)
                .map(mergePostsWithOwnerProfiles(profileMap));
    }

    @Override
    public CommentDto update(CommentDto postDto, Long id) {
        return commentRepository.findById(id)
                .map(user -> commentMapper.toEntity(postDto))
                .map(commentRepository::save)
                .map(commentMapper::toDto)
                .orElse(null);
    }

    @Override
    @Transactional
    public CommentDto save(CommentFormDto commentFormDto) {
        final var comment = new Comment();
        comment.setContent(commentFormDto.getContent());
        comment.setUsername(userService.getCurrentUser().getUsername());
        comment.setUser(userService.getCurrentUser());
        comment.setPostId(commentFormDto.getPostId());
        ProfileDto profileDto = profileClient.findByUsername(userService.getCurrentUser().getUsername());
        comment.setProfile(profileDto);

        if (!CollectionUtils.isEmpty(commentFormDto.getFiles())) {
            final var files = List.copyOf(commentFormDto.getFiles());
            validateFilesName(files);
            final var responses = this.storageClient.saveAll(files);
            comment.setAttachments(getAttachments(responses));
        }
        final Comment save = commentRepository.save(comment);

        return commentMapper.toDto(save);
    }

    @Override
    public Page<CommentDto> findByPostId(Long id, Pageable pageable) {
        return commentRepository.findByPostId(id, pageable)
                .map(commentMapper::toDto);
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

    private Function<CommentDto, CommentDto> mergePostsWithOwnerProfiles(Map<String, ProfileDto> profileMap) {
        return commentDto -> {
            String postUsername = commentDto.getUsername();
            ProfileDto ownerProfile = profileMap.get(postUsername);
            commentDto.setProfile(ownerProfile);
            return commentDto;
        };
    }


}
