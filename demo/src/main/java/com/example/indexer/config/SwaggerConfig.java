package com.example.indexer.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(title = "DemoAPI", description = """
            API Documentation
        """, contact = @Contact(name = "API Support", email = "support@becuai.com")), servers = {
        @Server(url = "/", description = "")
})
public class SwaggerConfig {
    
    @Bean
    public GroupedOpenApi searchApi() {
        return GroupedOpenApi.builder()
            .group("search")
            .displayName("뉴스검색")
            .pathsToMatch("/search/**")
            .build();
    }
}
