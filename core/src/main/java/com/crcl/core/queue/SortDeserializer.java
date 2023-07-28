package com.crcl.core.queue;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Sort;

import java.io.IOException;

public class SortDeserializer extends JsonDeserializer<Sort> {
    @Override
    public Sort deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        // Extract sorting information from the JSON node and construct a Sort object
        // Adjust the implementation according to your JSON structure

        String property = node.get("property").asText();  // Assuming the property name is stored under "property" field
        String direction = node.get("direction").asText();  // Assuming the sort direction is stored under "direction" field

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Sort.Order order = new Sort.Order(sortDirection, property);

        return Sort.by(order);
    }
}