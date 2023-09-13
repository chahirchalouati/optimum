package com.crcl.post.service.impl;

import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.post.client.StorageClient;
import com.crcl.post.domain.FileMapperType;
import com.crcl.post.domain.Image;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Video;
import com.crcl.post.mapper.FileMapperRegistry;
import com.crcl.post.service.FilesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilesServiceImpl implements FilesService {

    private final StorageClient storageClient;
    private final FileMapperRegistry fileMapperRegistry;

    @Override
    public void handleFiles(final List<MultipartFile> files, final Post post) {
        final var results = doStoreFiles(files);
        final var removables = new ArrayList<FileUploadResult>();

        results.stream()
                .filter(result -> StringUtils.containsIgnoreCase(result.getContentType(), "image"))
                .peek(removables::add)
                .map(fileMapperRegistry.getMapper(FileMapperType.IMAGE))
                .forEach(image -> post.getImages().add((Image) image));

        results.stream()
                .filter(result -> StringUtils.containsIgnoreCase(result.getContentType(), "video"))
                .peek(removables::add)
                .map(fileMapperRegistry.getMapper(FileMapperType.VIDEO))
                .forEach(video -> post.getVideos().add((Video) video));

        results.removeAll(removables);

        results.stream()
                .map(fileMapperRegistry.getMapper(FileMapperType.GENERIC))
                .forEach(file -> post.getGenericFiles().add(file));
    }

    private List<FileUploadResult> doStoreFiles(List<MultipartFile> files) {
        if (!files.isEmpty()) {
            return storageClient.saveAll(files);
        }
        return new ArrayList<>();
    }
}
