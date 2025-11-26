package br.com.estudaia.estudaia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.*;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
@ConfigurationProperties(prefix = "app.api")
@Getter
@Setter
public class ApiSecurityConfig {
    @Value("${app.api.key}")
    private String apiKey;
    
    @Value("${app.api.header-name}")
    private String headerName;
}