package com.cargocompass.app.offerservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfigConfiguration {

    @Bean
    public OpenAPI customOpenAPI(@Value("offer-service") String description,
                                 @Value("v1.0") String version) {
        return new OpenAPI()
                .info(new Info()
                        .title("Offer Service")
                        .version(version)
                        .description(description)
                        .license(new License().name("offer service")));
    }
}