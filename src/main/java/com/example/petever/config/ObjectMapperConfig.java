package com.example.petever.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {
    @Bean
    public ObjectMapper objectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new CustomConverter());
        return objectMapper;
    }
}
