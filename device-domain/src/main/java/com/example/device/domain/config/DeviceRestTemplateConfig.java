package com.example.device.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DeviceRestTemplateConfig {
    @Bean
    public RestTemplate deviceRestTemplate() {
        return new RestTemplate();
    }
}
