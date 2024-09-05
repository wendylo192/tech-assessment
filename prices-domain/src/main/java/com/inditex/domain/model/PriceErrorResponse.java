package com.inditex.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PriceErrorResponse {

    private final String code;
    private final String message;
    private final Optional<List<String>> details;
    private final LocalDateTime timestamp;

    public PriceErrorResponse(String code, String message, LocalDateTime timestamp) {
        this(code, message, Optional.empty(), timestamp);
    }

    public PriceErrorResponse(String code, String message, Optional<List<String>> details, LocalDateTime timestamp) {
        this.code = code;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

    // Getters only
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Optional<List<String>> getDetails() {
        return details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
