package com.rexqwer.statemachinedemoapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(@Value("${server.port}") String localPort) {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API")
                        .version("1.0")
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:".concat(localPort))
                                .description("API server local")));
    }
}