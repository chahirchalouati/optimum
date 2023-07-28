package com.crcl.core.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @JsonProperty(value = "message", index = 1)
    private String defaultErrorMessage = "bad request";
    @JsonProperty(value = "errors", index = 2)
    private Map<String, String> errors;

    @JsonProperty(index = 3)
    private LocalDateTime timestamp = LocalDateTime.now(Clock.systemDefaultZone());

    public ErrorResponse(Map<String, String> errors) {
        this.errors = errors;
    }

    public ErrorResponse(String defaultErrorMessage) {
        this.defaultErrorMessage = defaultErrorMessage;
    }
}
