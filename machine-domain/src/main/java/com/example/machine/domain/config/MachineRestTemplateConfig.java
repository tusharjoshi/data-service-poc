package com.example.machine.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MachineRestTemplateConfig {
    @Bean
    public RestTemplate machineRestTemplate() {
        return new RestTemplate();
    }
}
