package com.romeoscode.url_shortner.api;

import com.romeoscode.url_shortner.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 21:00
 * projectName url_shortner
 **/

@RestController
@RequiredArgsConstructor
@Tag(name = "Redirect", description = "Root-level redirection for shortened URLs")
public class RedirectController {

    private final UrlService urlService;

    @GetMapping("/{shortCode:[a-zA-Z0-9]{6}}")
    @Operation(summary = "Redirect to original URL", description = "Redirects the user to the original long URL based on the provided short code.")
    public void redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        response.sendRedirect(urlService.getByShortCode(shortCode).getOriginalUrl());
    }
}
