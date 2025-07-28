package com.example.demo.controller;  // 실제 패키지명으로 변경하세요

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @Value("${app.version:1.0}")
    private String version;

    @Value("${app.environment:blue}")
    private String environment;

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("environment", environment);
        response.put("version", version);
        response.put("timestamp", LocalDateTime.now());
        return response;
    }

    @GetMapping("/")
    public String home() {
        return String.format(
                "<div style='text-align: center; margin-top: 100px; font-family: Arial;'>" +
                        "<h1 style='color: %s; font-size: 4em; text-shadow: 2px 2px 4px rgba(0,0,0,0.3);'>%s</h1>" +
                        "<h2 style='color: #333;'>Version: %s</h2>" +
                        "<h3 style='color: #666;'>🚀 GREEEEEEEEEnffffff-Green 배포 테스트</h3>" +
                        "<p style='color: #999; font-size: 1.2em;'>현재 시간: %s</p>" +
                        "<div style='margin-top: 30px;'>" +
                        "<a href='/health' style='background: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin: 5px;'>Health Check</a>" +
                        "</div>" +
                        "</div>",
                environment, environment.toUpperCase(), version, LocalDateTime.now()
        );
    }
}