package com.nitya.spring_security.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ApiErrorResponse(
        @Schema(description = "Error code")
        int errorCode,
        @Schema(description = "Error description")
        String description) {
}
