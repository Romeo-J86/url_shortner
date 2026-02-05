package com.romeoscode.url_shortner.config;

import com.romeoscode.url_shortner.util.AppDocs;
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
                        .title(AppDocs.TITLE)
                        .version(AppDocs.VERSION)
                        .description(AppDocs.DESCRIPTION)
                        .contact(new Contact()
                                .name(AppDocs.Contacts.NAME)
                                .email(AppDocs.Contacts.EMAIL))
                        .license(new License()
                                .name(AppDocs.Licence.NAME)
                        ));
    }
}
