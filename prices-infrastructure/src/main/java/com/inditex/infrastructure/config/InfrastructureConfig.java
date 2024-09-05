package com.inditex.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inditex.infrastructure.mapper.PriceMapper;
import com.inditex.infrastructure.mapper.PriceMapperImpl;

@Configuration
public class InfrastructureConfig {

    @Bean
    public PriceMapper priceMapper() {
        return new PriceMapperImpl();
    }
}