package com.latitude.genoapay.codingchallenge.config;

import com.latitude.genoapay.codingchallenge.service.StockService;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public StockService stockService() {
        return new StockService();
    }
}