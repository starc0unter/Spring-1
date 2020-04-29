package com.chentsov.hw4.rest.errorResponse;

import lombok.Data;

@Data
public final class ErrorResponse {
    private final int status;
    private final String message;
    private final long timestamp;
}
