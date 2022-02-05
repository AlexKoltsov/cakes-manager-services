package com.koltsov.cakes.manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(buildProperties.getName())
                        .description(buildProperties.get("description"))
                        .contact(new Contact()
                                .name("Koltsov Alexander")
                                .email("alexander.koltsov.work@gmail.com")
                        )
                        .version(buildProperties.getVersion())
                );
    }
}
