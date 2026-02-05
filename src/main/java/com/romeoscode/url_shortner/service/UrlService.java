package com.romeoscode.url_shortner.service;

import com.romeoscode.url_shortner.domain.UrlEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 09:24
 * projectName url_shortner
 **/

public interface UrlService {
    /**
     * Method attempts to create a short url
     * @param originalUrl
     * @param expiryDays
     * @return
     */
    UrlEntity createShortUrl(String originalUrl, Integer expiryDays);

    /**
     * Method attempts to retrieve a specific short url
     * @param shortCode
     * @return
     */
    UrlEntity getByShortCode(String shortCode);

    /**
     * Method attempts to retrieve paged short urls
     * @param pageable
     * @return
     */
    Page<UrlEntity> getAllUrls(Pageable pageable);

    /**
     * Method attempts to delete a specific short url
     * @param shortCode
     */
    void deleteByShortCode(String shortCode);
}
