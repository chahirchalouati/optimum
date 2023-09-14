package com.crcl.post.service.impl;

import com.crcl.core.dto.queue.ProcessableImage;
import com.crcl.core.dto.queue.ProcessableVideo;
import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.post.client.StorageClient;
import com.crcl.post.domain.FileMapperType;
import com.crcl.post.domain.Image;
import com.crcl.post.domain.Post;
import com.crcl.post.domain.Video;
import com.crcl.post.dto.FileRecordDto;
import com.crcl.post.mapper.FileMapperRegistry;
import com.crcl.post.queue.EventQueuePublisher;
import com.crcl.post.service.FilesService;
import com.crcl.post.utils.FileExtensionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilesServiceImpl implements FilesService {

    private final StorageClient storageClient;
    private final FileMapperRegistry fileMapperRegistry;
    private final EventQueuePublisher eventQueuePublisher;

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
            List<FileUploadResult> fileUploadResults = storageClient.saveAll(files);
            return fileUploadResults;
        }
        return new ArrayList<>();
    }
    private Consumer<FileRecordDto> publishImageUploadEvent() {
        return record -> {
            final boolean isImage = FileExtensionUtils.isImage(record.getName());
            if (isImage) {
                FileUploadResult result = new FileUploadResult()
                        .setContentType(record.getType())
                        .setBucket(record.getBucket())
                        .setEtag(record.getTag())
                        .setName(record.getName())
                        .setVersion(record.getVersion());
                ProcessableImage request = new ProcessableImage();
                request.setResult(result);
                request.setLocalDateTime(LocalDateTime.now(Clock.systemDefaultZone()));
                eventQueuePublisher.publishProcessableImageEvent(request);
            }
        };
    }

    private Consumer<FileRecordDto> publishVideoUploadEvent() {
        return record -> {
            final boolean isVideo = FileExtensionUtils.isVideo(record.getName());
            if (isVideo) {
                FileUploadResult result = new FileUploadResult()
                        .setContentType(record.getType())
                        .setBucket(record.getBucket())
                        .setEtag(record.getTag())
                        .setName(record.getName())
                        .setVersion(record.getVersion());
                ProcessableVideo request = new ProcessableVideo();
                request.setResult(result);
                request.setLocalDateTime(LocalDateTime.now(Clock.systemDefaultZone()));
                eventQueuePublisher.publishProcessableVideoEvent(request);
            }
        };
    }
}
