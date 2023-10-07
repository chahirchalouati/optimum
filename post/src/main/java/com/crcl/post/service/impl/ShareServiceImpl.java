package com.crcl.post.service.impl;

import com.crcl.core.exceptions.EntityNotFoundException;
import com.crcl.post.client.IdpClient;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Share;
import com.crcl.post.repository.ShareRepository;
import com.crcl.post.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;
    private final IdpClient idpClient;

    @Override
    public Share save(Share share) {
        return shareRepository.save(share);
    }

    @Override
    public List<Share> saveAll(List<Share> entitiesDto) {
        return shareRepository.saveAll(entitiesDto);
    }

    @Override
    public void deleteById(String id) {
        shareRepository.deleteById(id);
    }

    @Override
    public Share findById(String id) {
        return shareRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Share> findAll() {
        return shareRepository.findAll();
    }

    @Override
    public Page<Share> findAll(Pageable pageable) {
        return shareRepository.findAll(pageable);
    }

    @Override
    public Share update(Share share, String id) {
        Share existingShare = shareRepository.findById(id).orElse(null);
        if (existingShare != null) {
            return shareRepository.save(share);
        }
        return null;
    }

    @Override
    public void handleShares(List<String> sharedWithUsers, Post post) {
        if (sharedWithUsers.isEmpty()) return;
        final var users = idpClient.findByUsername(new LinkedHashSet<>(sharedWithUsers));
        if (users != null) {
            List<Share> shares = users.stream()
                    .map(userDto -> new Share(post.getId(), userDto))
                    .toList();
            shareRepository.saveAll(shares);
        }
    }
}