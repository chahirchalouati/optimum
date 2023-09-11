package com.crcl.post.mapper;

import com.crcl.core.dto.responses.FileUploadResult;
import com.crcl.post.domain.FileMapperType;
import com.crcl.post.domain.GenericFile;
import com.crcl.post.domain.Image;
import com.crcl.post.domain.Video;
import com.crcl.post.exceptions.FileMapperNotFound;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Component
public class FileMapperRegistry {

    public Function<FileUploadResult, ? extends GenericFile> getMapper(FileMapperType type) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        return switch (type) {
            case GENERIC -> genericFileMapper(atomicInteger.incrementAndGet());
            case IMAGE -> imageFileMapper(atomicInteger.incrementAndGet());
            case VIDEO -> videoFileMapper(atomicInteger.incrementAndGet());
            default -> throw new FileMapperNotFound("Unable to fine fileMapper for type: " + type.name());
        };
    }


    private Function<FileUploadResult, GenericFile> genericFileMapper(Integer index) {
        return genericFile -> new GenericFile(index)
                .setId(genericFile.getEtag())
                .setUrl(genericFile.getLink())
                .setContentType(genericFile.getContentType());
    }

    private Function<FileUploadResult, GenericFile> imageFileMapper(Integer index) {
        return file -> new Image()
                .setId(file.getEtag())
                .setIndex(index)
                .setContentType(file.getContentType())
                .setUrl(file.getLink());
    }

    private Function<FileUploadResult, GenericFile> videoFileMapper(Integer index) {
        return file -> new Video()
                .setId(file.getEtag())
                .setIndex(index)
                .setUrl(file.getLink());
    }
}
