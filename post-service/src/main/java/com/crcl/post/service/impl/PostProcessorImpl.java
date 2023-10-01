package com.crcl.post.service.impl;

import com.crcl.post.domain.Post;
import com.crcl.post.dto.CreatePostRequest;
import com.crcl.post.repository.PostRepository;
import com.crcl.post.service.FilesService;
import com.crcl.post.service.PostProcessor;
import com.crcl.post.service.ShareService;
import com.crcl.post.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostProcessorImpl implements PostProcessor {

    private final TagService tagService;
    private final ShareService shareService;
    private final FilesService filesService;
    private final PostRepository postRepository;

    @Override
    public void processPostAsync(SecurityContext securityContext, CreatePostRequest request, Post post) {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.execute(() -> {
                log.debug("Received create post request. Security context: [{}], Request details: [{}], Post details: [{}]",
                        securityContext, request, post);
                try {
                    SecurityContextHolder.setContext(securityContext);
                    filesService.handleFiles(request.getFiles(), post);
                    shareService.handleShares(request.getSharedWithUsers(), post);
                    tagService.processTags(request.getTags(), post);
                    this.postRepository.save(post);

                } catch (Exception e) {
                    log.error("An error occurred while processing the create post request: {}", e.getMessage(), e);
                } finally {
                    SecurityContextHolder.clearContext();
                }
            });
        }
    }
}
