package io.horatius.farouk_university.configurations.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/*
 * Enabling CORS in reactive webflux application is different from a normal Spring Boot application
 * Courtesy of:
 * https://stackoverflow.com/questions/46978794/enable-cors-in-spring-5-webflux
 */
@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Access-Control-Allow-Origin", "Authorization", "Content-Type")
                .maxAge(36000);
    }
}
