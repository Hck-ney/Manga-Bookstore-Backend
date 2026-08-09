package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 1. Allow specific origins (Replace with your Next.js URL)
        // Use "*" for development, but be specific in production for security
        config.setAllowedOriginPatterns(Arrays.asList("http://localhost:3000", "https://your-production-domain.com"));

        // 2. Allow specific methods
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // 3. Allow specific headers
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));

        // 4. Allow credentials (cookies, auth headers) if needed
        config.setAllowCredentials(true);

        // 5. Expose headers if the frontend needs to read them
        config.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Apply to all paths
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}