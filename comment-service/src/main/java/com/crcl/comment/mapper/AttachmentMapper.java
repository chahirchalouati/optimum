package com.crcl.comment.mapper;

import com.crcl.comment.configuration.properties.ImageSizesProperties;
import com.crcl.comment.domain.Attachment;
import com.crcl.comment.dto.AttachmentDto;
import com.crcl.core.utils.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class AttachmentMapper implements GenericMapper<Attachment, AttachmentDto> {

    @Autowired
    private ImageSizesProperties imageSizesProperties;

    @Override
    public AttachmentDto toDto(Attachment entity) {
        if (entity == null) {
            return null;
        }

        AttachmentDto attachmentDto = createAttachmentDto(entity);
        Map<String, Object> map = entity.getAdditionalData();

        if (map != null && !map.isEmpty() && map.size() == imageSizesProperties.getSizes().size()) {
            attachmentDto.setAdditionalData(new LinkedHashMap<>(map));
        } else {
            imageSizesProperties.getSizes().keySet()
                    .stream()
                    .filter(imageSize -> {
                        assert map != null;
                        return !map.containsKey(imageSize);
                    })
                    .forEach(missingSize -> {
                        AttachmentDto dto = createAttachmentDto(entity);
                        map.put(missingSize, dto);
                    });

            attachmentDto.setAdditionalData(new LinkedHashMap<>(map));
        }

        attachmentDto.setLink(entity.getLink());
        return attachmentDto;
    }

    private AttachmentDto createAttachmentDto(Attachment entity) {
        AttachmentDto dto = new AttachmentDto();
        dto.setEtag(entity.getEtag());
        dto.setName(entity.getName());
        dto.setBucket(entity.getBucket());
        dto.setVersion(entity.getVersion());
        dto.setContentType(entity.getContentType());
        dto.setOrientation(entity.getOrientation());
        return dto;
    }


}
