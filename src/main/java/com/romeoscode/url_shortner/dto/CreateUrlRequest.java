package com.romeoscode.url_shortner.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 09:19
 * projectName url_shortner
 **/

@Builder
public record CreateUrlRequest(
        @NotBlank(message = "URL is required")
        @Pattern(
                regexp = "^https?://.*",
                message = "URL must start with http:// or https://"
        )
        String url,

        @Min(value = 1, message = "Expiry days must be at least 1")
        Integer expiryDays
){}
