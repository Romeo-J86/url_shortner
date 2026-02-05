package com.romeoscode.url_shortner.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * createdBy romeo
 * createdDate 5/2/2026
 * createdTime 10:19
 * projectName url_shortner
 **/

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("URL Shortener API")
                        .version("1.0.0")
                        .description("REST API for shortening long URLs and tracking click analytics.")
                        .contact(new Contact()
                                .name("API Support")
                                .url("https://github.com/romeo/url_shortener"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
