package com.romeoscode.url_shortner.service.impl;

import com.romeoscode.url_shortner.domain.UrlEntity;
import com.romeoscode.url_shortner.exception.UrlExpiredException;
import com.romeoscode.url_shortner.exception.UrlNotFoundException;
import com.romeoscode.url_shortner.persistence.UrlRepository;
import com.romeoscode.url_shortner.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 09:29
 * projectName url_shortner
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {


    private final UrlRepository urlRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    @Value("${app.base62.chars}")
    private String base62Chars;

    @Value("${app.shortcode.length}")
    private int shortCodeLength;

    @Value("${app.max.retries}")
    private int maxRetries;

    @Override
    @Transactional
    public UrlEntity createShortUrl(String originalUrl, Integer expiryDays) {
        String shortCode = generateUniqueShortCode();

        LocalDateTime expiresAt = null;
        if (expiryDays != null && expiryDays > 0) {
            expiresAt = LocalDateTime.now().plusDays(expiryDays);
        }

        UrlEntity urlEntity = UrlEntity.builder()
                .originalUrl(originalUrl)
                .shortCode(shortCode)
                .expiresAt(expiresAt)
                .clickCount(0L)
                .build();

        UrlEntity saved = urlRepository.save(urlEntity);
        log.info("Created short URL: {} -> {}", shortCode, originalUrl);

        return saved;
    }

    @Override
    @Transactional
    public UrlEntity getByShortCode(String shortCode) {
        UrlEntity urlEntity = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));

        if (urlEntity.isExpired()) {
            throw new UrlExpiredException(shortCode);
        }

        urlEntity.incrementClickCount();
        urlRepository.save(urlEntity);

        return urlEntity;
    }

    @Override
    public Page<UrlEntity> getAllUrls(Pageable pageable) {
        return urlRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void deleteByShortCode(String shortCode) {
        UrlEntity urlEntity = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));

        urlRepository.delete(urlEntity);
        log.info("Deleted short URL: {}", shortCode);
    }

    private String generateUniqueShortCode() {
        int attempts = 0;

        while (attempts < maxRetries) {
            String shortCode = generateRandomShortCode();

            if (!urlRepository.existsByShortCode(shortCode)) {
                return shortCode;
            }

            attempts++;
        }

        throw new RuntimeException("Failed to generate unique short code after " + maxRetries + " attempts");
    }

    private String generateRandomShortCode() {
        StringBuilder sb = new StringBuilder(shortCodeLength);

        for (int i = 0; i < shortCodeLength; i++) {
            int randomIndex = secureRandom.nextInt(base62Chars.length());
            sb.append(base62Chars.charAt(randomIndex));
        }

        return sb.toString();
    }
}

