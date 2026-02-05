package com.romeoscode.url_shortner.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 09:56
 * projectName url_shortner
 **/

@Data
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
