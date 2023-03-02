package com.crcl.comment.converters;

import com.crcl.comment.dto.ProfileDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Slf4j
@Converter
public class ProfileDtoConverter implements AttributeConverter<ProfileDto, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ProfileDto attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize ProfileDto object: {}", attribute, e);
            return null;
        }
    }

    @Override
    public ProfileDto convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            return objectMapper.readValue(dbData, ProfileDto.class);
        } catch (IOException e) {
            log.error("Failed to deserialize ProfileDto object: {}", dbData, e);
            return null;
        }
    }
}
