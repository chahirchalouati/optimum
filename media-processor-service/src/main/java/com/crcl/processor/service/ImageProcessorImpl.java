package com.crcl.processor.service;


import com.crcl.common.dto.responses.FileUploadResult;
import com.crcl.common.queue.ImageUploadEvent;
import com.crcl.processor.clients.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageProcessorImpl implements ImageProcessor {

    private final UserService userService;
    private final StorageClient storageClient;

    @Override
    public void process(ImageUploadEvent event) {
        userService.getCurrentUser().subscribe(System.out::println);

        FileUploadResult response = event.getResponse();
        storageClient.getObject(response.getName(), response.getEtag()).subscribe(
                byteArrayResource -> System.out.println(byteArrayResource.contentLength())
        );
    }
}
