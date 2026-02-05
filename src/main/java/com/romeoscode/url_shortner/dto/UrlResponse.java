package com.romeoscode.url_shortner.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 09:21
 * projectName url_shortner
 **/

@Builder
public record UrlResponse(
        Long id,
        String originalUrl,
        String shortCode,
        String shortUrl,
        LocalDateTime createdAt,
        LocalDateTime expiresAt,
        Long clickCount
){}
