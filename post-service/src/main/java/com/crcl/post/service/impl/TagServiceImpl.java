package com.crcl.post.service.impl;

import com.crcl.post.client.IdpClient;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Tag;
import com.crcl.post.handlers.TagHandler;
import com.crcl.post.repository.TagRepository;
import com.crcl.post.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final IdpClient idpClient;
    private final Set<TagHandler> tagHandlers;

    @Override
    public void processTags(final List<Tag> tags, final @Nullable Post post) {
        if (tags.isEmpty()) return;
        tagHandlers.forEach(tagHandler -> tagHandler.handle(tags, post));
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
        return tagRepository.saveAll(entitiesDto);
    }

    @Override
    public void deleteById(String id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag findById(String id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Page<Tag> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag update(Tag tag, String id) {
        final Tag existingTag = tagRepository.findById(id).orElse(null);
        if (existingTag != null) {
            existingTag.setValue(tag.getValue());
            existingTag.setKind(tag.getKind());
            return tagRepository.save(existingTag);
        }
        return null;
    }
}
