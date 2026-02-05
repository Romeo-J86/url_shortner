package com.romeoscode.url_shortner.api;

import com.romeoscode.url_shortner.domain.UrlEntity;
import com.romeoscode.url_shortner.persistence.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RedirectControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlRepository urlRepository;

    @BeforeEach
    void setUp() {
        urlRepository.deleteAll();
    }

    @Test
    void shouldRedirectToOriginalUrl() throws Exception {
        // Given
        String shortCode = "ABCDEF";
        String originalUrl = "https://www.google.com";
        UrlEntity urlEntity = UrlEntity.builder()
                .shortCode(shortCode)
                .originalUrl(originalUrl)
                .clickCount(0L)
                .build();
        urlRepository.save(urlEntity);

        // When & Then
        mockMvc.perform(get("/" + shortCode))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(originalUrl));
    }

    @Test
    void shouldReturn404ForInvalidShortCode() throws Exception {
        mockMvc.perform(get("/123456"))
                .andExpect(status().isNotFound());
    }
}
