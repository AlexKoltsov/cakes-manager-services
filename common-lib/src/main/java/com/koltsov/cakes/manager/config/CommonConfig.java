package com.koltsov.cakes.manager.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(basePackages = "com.koltsov.cakes.manager.client")
@ComponentScan(basePackages = "com.koltsov.cakes.manager")
@Configuration
public class CommonConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
