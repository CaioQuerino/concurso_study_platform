package br.com.estudaia.estudaia.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class ApiKeyAuthFilter implements Filter {

    @Autowired
    private ApiSecurityConfig apiSecurityConfig;

    // Endpoints públicos que não precisam de API Key
    private final List<String> publicEndpoints = Arrays.asList(
    "/api/health",
        "/swagger-ui",
        "/v3/api-docs",
        "/error"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        // Verifica se o endpoint é público
        if (isPublicEndpoint(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        String apiKey = httpRequest.getHeader(apiSecurityConfig.getHeaderName());

        if (apiKey == null || apiKey.trim().isEmpty()) {
            sendErrorResponse(httpResponse, "API Key não fornecida");
            return;
        }

        if (!apiKey.equals(apiSecurityConfig.getApiKey())) {
            sendErrorResponse(httpResponse, "API Key inválida");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicEndpoint(String requestURI) {
        return publicEndpoints.stream().anyMatch(requestURI::startsWith);
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String jsonResponse = String.format(
            "{\"success\": false, \"message\": \"%s\", \"data\": null, \"timestamp\": \"%s\"}",
            message,
            java.time.LocalDateTime.now()
        );
        
        response.getWriter().write(jsonResponse);
    }
}