package com.crcl.post.service.impl;

import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.post.client.StorageClient;
import com.crcl.post.domain.Post;
import com.crcl.post.mapper.FileMapper;
import com.crcl.post.service.FilesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilesServiceImpl implements FilesService {

    private final StorageClient storageClient;
    private final Set<FileMapper> fileMappers;

    @Override
    public List<FileUploadResult> handleFiles(final List<MultipartFile> files, final Post post) {
        if (files.isEmpty()) return Collections.emptyList();

        final var fileUploadResults = this.storageClient.saveAll(files);
        fileMappers.stream()
                .parallel()
                .forEach(fileMapper -> fileMapper.map(fileUploadResults, post));

        return fileUploadResults;
    }

}
