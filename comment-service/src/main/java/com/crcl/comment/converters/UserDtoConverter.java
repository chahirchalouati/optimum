package com.crcl.comment.converters;

import com.crcl.comment.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@Converter
public class UserDtoConverter implements AttributeConverter<UserDto, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(UserDto attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize UserDto object: {}", attribute, e);
            return null;
        }
    }

    @Override
    public UserDto convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            return objectMapper.readValue(dbData, UserDto.class);
        } catch (IOException e) {
            log.error("Failed to deserialize UserDto object: {}", dbData, e);
            return null;
        }
    }
}
