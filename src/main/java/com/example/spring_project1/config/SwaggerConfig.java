package com.example.spring_project1.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SwaggerConfig {

    private final String REST_API_ROOT = "/api/v1/**";
    private final String REST_API_GROUP = "REST API";

    private final String COMMON_API_ROOT = "/**/*";
    private final String COMMON_API_GROUP = "COMMON API";
    private final String[] COMMON_EXCLUDE_ROOT = {"/api/**/*"};

    @Bean
    public GroupedOpenApi restApi(){

        return GroupedOpenApi.builder()
            .pathsToMatch(REST_API_ROOT)
            .group(REST_API_GROUP)
            .build();
    }

    @Bean
    public GroupedOpenApi commonApi(){

        return GroupedOpenApi.builder()
            .pathsToMatch(COMMON_API_ROOT)
            .pathsToExclude(COMMON_EXCLUDE_ROOT)
            .group(COMMON_API_GROUP)
            .build();
    }
}
