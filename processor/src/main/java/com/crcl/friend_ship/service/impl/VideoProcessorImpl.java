package com.crcl.friend_ship.service.impl;

import com.crcl.core.dto.queue.CreatePostPayload;
import com.crcl.core.dto.queue.events.AuthenticatedQEvent;
import com.crcl.friend_ship.clients.StorageClient;
import com.crcl.friend_ship.queue.EventQueuePublisher;
import com.crcl.friend_ship.service.UserService;
import com.crcl.friend_ship.service.VideoProcessor;
import com.crcl.friend_ship.utils.FileExtensionUtils;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
@Slf4j
@RequiredArgsConstructor
public class VideoProcessorImpl implements VideoProcessor {

    private final UserService userService;
    private final StorageClient storageClient;
    private final MinioClient minioClient;
    private final EventQueuePublisher eventQueuePublisher;

    @Override
    public void process(AuthenticatedQEvent<CreatePostPayload> event) {
        event.getPayload().getFiles().stream()
                .filter(result -> FileExtensionUtils.isVideo(result.getName()))
                .forEach(result -> storageClient.getObject(result.getName(), result.getEtag())
                        .zipWith(userService.getCurrentUser())
                        .log("start receiveing video ...", Level.INFO)
                        .subscribe());

    }
}
