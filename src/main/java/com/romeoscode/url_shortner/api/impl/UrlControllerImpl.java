package com.romeoscode.url_shortner.api.impl;

import com.romeoscode.url_shortner.api.UrlController;
import com.romeoscode.url_shortner.domain.UrlEntity;
import com.romeoscode.url_shortner.dto.CreateUrlRequest;
import com.romeoscode.url_shortner.dto.UrlResponse;
import com.romeoscode.url_shortner.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 09:51
 * projectName url_shortner
 **/

@RestController
@RequiredArgsConstructor
public class UrlControllerImpl implements UrlController {

    private final UrlService urlService;

    @Override
    public UrlResponse createShortUrl(CreateUrlRequest request) {
        return toResponse(urlService.createShortUrl(request.url(), request.expiryDays()));
    }

    @Override
    public Page<UrlResponse> getAllUrls(Pageable pageable) {
        return urlService.getAllUrls(pageable)
                .map(this::toResponse);
    }

    @Override
    public UrlResponse getUrlDetails(String shortCode) {
        return toResponse(urlService.getByShortCode(shortCode));
    }

    @Override
    public void deleteUrl(String shortCode) {
        urlService.deleteByShortCode(shortCode);
    }

    @Override
    public void redirectToOriginalUrl(String shortCode, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(urlService.getByShortCode(shortCode).getOriginalUrl());
    }

    private UrlResponse toResponse(UrlEntity entity) {
        String shortUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{shortCode}")
                .buildAndExpand(entity.getShortCode())
                .toUriString();

        return UrlResponse.builder()
                .id(entity.getId())
                .originalUrl(entity.getOriginalUrl())
                .shortCode(entity.getShortCode())
                .shortUrl(shortUrl)
                .createdAt(entity.getCreatedAt())
                .expiresAt(entity.getExpiresAt())
                .clickCount(entity.getClickCount())
                .build();
    }
}

