package com.crcl.post.converters;
import com.crcl.post.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
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
