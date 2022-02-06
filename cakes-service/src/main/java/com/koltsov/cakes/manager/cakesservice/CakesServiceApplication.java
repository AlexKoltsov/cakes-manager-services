package com.koltsov.cakes.manager.cakesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CakesServiceApplication {

    // test commit

    public static void main(String[] args) {
        SpringApplication.run(CakesServiceApplication.class, args);
    }

}
