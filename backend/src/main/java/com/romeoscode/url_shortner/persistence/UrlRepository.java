package com.romeoscode.url_shortner.persistence;

import com.romeoscode.url_shortner.domain.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 09:07
 * projectName url_shortner
 **/

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {

    Optional<UrlEntity> findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);
}
