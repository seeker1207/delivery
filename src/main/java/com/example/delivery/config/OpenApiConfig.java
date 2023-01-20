package com.example.delivery.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("딜리버리 주문 서비스")
                .description("바로고 딜리버리 주문 서비스 입니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
