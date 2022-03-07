package com.kadmos.service.saveAccount.restController;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

public class SwaggerConfiguration {
    private static final String TITLE = "Save Account Service";
    private static final String DESCRIPTION = "Application used for save Account service";
    private static final String VERSION = "1.0";
    private static final String URL = "https://github.com/ruhollahshafyi";


    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("saveaccount")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customerServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(TITLE)
                        .description(DESCRIPTION)
                        .version(VERSION)
                        .license(new License().name("Apache 2.0").url(URL)))
                .externalDocs(new ExternalDocumentation()
                        .description("Save account service Wiki Documentation")
                        .url("https://github.com/ruhollahshafyi"));
    }
}
