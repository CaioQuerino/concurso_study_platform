package br.com.estudaia.estudaia.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.estudaia.estudaia.dto.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:8100", "http://localhost:3000"})
public class HealthController {

    @GetMapping("/health")
    public ApiResponse<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Application is running");
        return ApiResponse.success(response);
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> apiInfo() {
        Map<String, String> response = new HashMap<>();
        response.put("name", "EstudaIA API");
        response.put("version", "1.0.0");
        response.put("description", "Plataforma de estudos para concursos com IA");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(response);
    }
}