package com.romeoscode.url_shortner.api;

import com.romeoscode.url_shortner.dto.CreateUrlRequest;
import com.romeoscode.url_shortner.dto.UrlResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 10:00
 * projectName url_shortner
 **/

public interface UrlController {

    @PostMapping("/api/urls")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new short URL",
            description = "Generates a unique 6-character short code for a given long URL."
    )
    UrlResponse createShortUrl(@Valid @RequestBody CreateUrlRequest request);

    @GetMapping("/api/urls/{shortCode}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get URL details",
            description =
                    """
                    Retrieves information about a specific short URL by
                    its code without incrementing click count.
                    """
    )
    UrlResponse getUrlDetails(@PathVariable String shortCode);

    @GetMapping("/api/urls")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "List all shortened URLs",
            description = "Retrieves a paginated list of all URLs stored in the database."
    )
    Page<UrlResponse> getAllUrls(
            @PageableDefault(size = 8, sort = "createdAt", direction = DESC) Pageable pageable);

    @DeleteMapping("/api/urls/{shortCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a short URL",
            description = "Removes a shortened URL from the database by its short code."
    )
    void deleteUrl(@PathVariable String shortCode);

    @GetMapping("/{shortCode}")
    @Operation(
            summary = "Redirect to original URL",
            description = """
                    Redirects the user to the original long URL and increments the click
                    analytics counter.
                    """
    )
    void redirectToOriginalUrl(@PathVariable String shortCode, HttpServletResponse response)
            throws IOException;
}
