package com.dkowalczyk.psinder_app.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private int status;
    private String code;
    @Builder.Default
    private long timestamp = System.currentTimeMillis();
}