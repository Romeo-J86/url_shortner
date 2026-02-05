package com.romeoscode.url_shortner.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 09:05
 * projectName url_shortner
 **/

@Entity
@Table(name = "url_entity", indexes = {
        @Index(name = "idx_short_code", columnList = "shortCode")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2048)
    private String originalUrl;

    @Column(nullable = false, unique = true, length = 10)
    private String shortCode;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    @Builder.Default
    private Long clickCount = 0L;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public void incrementClickCount() {
        this.clickCount++;
    }

    public boolean isExpired() {
        return expiresAt != null && LocalDateTime.now().isAfter(expiresAt);
    }
}

