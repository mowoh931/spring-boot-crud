package com.baar.springbootcrud.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("UserDetails API")
                        .description("Spring boot with MySQL CRUD application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("UserDetails Documentation")
                        .url("https://github.com/mowoh931/spring-boot-crud?tab=readme-ov-file"));
    }


}
