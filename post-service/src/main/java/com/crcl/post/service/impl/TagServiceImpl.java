package com.crcl.post.service.impl;

import com.crcl.core.dto.UserDto;
import com.crcl.post.client.IdpClient;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Tag;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.repository.TagRepository;
import com.crcl.post.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final IdpClient idpClient;

    @Override
    public void handleTags(CreatePostRequest request, Post post) {
        Set<String> requestTagNames = request.getTags().stream()
                .filter(Tag::isSystem)
                .map(Tag::getName)
                .collect(Collectors.toSet());

        List<Tag> existingTags = tagRepository.findByNameIn(requestTagNames);
        Set<String> existingTagNames = existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        List<Tag> newTags = request.getTags().stream()
                .filter(tag -> !existingTagNames.contains(tag.getName()))
                .collect(Collectors.toList());

        tagRepository.saveAll(newTags);
    }

    @Override
    public void handleTaggedUsers(CreatePostRequest request, Post post) {
        Set<String> usernames = new LinkedHashSet<>(request.getTaggedUsers());
        Collection<String> existingUsernames = idpClient.findByUsername(usernames)
                .stream()
                .map(UserDto::getUsername)
                .toList();

        existingUsernames.forEach(usernames::remove);

        List<Tag> userTags = usernames.stream()
                .map(username -> new Tag().setName(username).setKind(Tag.TagKind.USER))
                .collect(Collectors.toList());

        tagRepository.saveAll(userTags);
    }

    @Override
    public Set<Tag> findByEntityId(String id) {
        return tagRepository.findByEntityId(id);
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> saveAll(List<Tag> entitiesDto) {
        return null;
    }

    @Override
    public void deleteById(String string) {

    }

    @Override
    public Tag findById(String string) {
        return null;
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }

    @Override
    public Page<Tag> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Tag update(Tag tag, String string) {
        return null;
    }


}
