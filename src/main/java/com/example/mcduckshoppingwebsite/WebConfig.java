package com.example.mcduckshoppingwebsite;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("http://localhost:5173") // Allow only this origin, use "*" for all
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed methods
                .allowCredentials(true)
                .maxAge(3600); // Cache the preflight response for 1 hour
    }
}

