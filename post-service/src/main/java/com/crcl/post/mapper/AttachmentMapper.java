package com.crcl.post.mapper;

import com.crcl.common.utils.generic.GenericMapper;
import com.crcl.post.configuration.properties.ImageSizesProperties;
import com.crcl.post.domain.Attachment;
import com.crcl.post.dto.AttachmentDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class AttachmentMapper implements GenericMapper<Attachment, AttachmentDto> {

    @Autowired
    private ImageSizesProperties imageSizesProperties;

    @Override
    public AttachmentDto toDto(Attachment entity) {
        if (entity == null) {
            return null;
        }
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setEtag(entity.getEtag());
        attachmentDto.setName(entity.getName());
        attachmentDto.setBucket(entity.getBucket());
        attachmentDto.setVersion(entity.getVersion());
        attachmentDto.setContentType(entity.getContentType());
        Map<String, Object> map = entity.getAdditionalData();

        if (map != null && !map.isEmpty() && map.size() == imageSizesProperties.getSizes().size()) {
            attachmentDto.setAdditionalData(new LinkedHashMap<>(map));
        } else {
            Set<String> missingSizes = imageSizesProperties.getSizes().keySet()
                    .stream()
                    .filter(imageSize -> {
                        assert map != null;
                        return map.containsKey(imageSize);
                    })
                    .collect(Collectors.toSet());
            for (String missingSize : missingSizes) {
                map.put(missingSize,attachmentDto);
            }
            attachmentDto.setAdditionalData(new LinkedHashMap<>(map));
        }
        attachmentDto.setLink(entity.getLink());

        return attachmentDto;
    }
}
