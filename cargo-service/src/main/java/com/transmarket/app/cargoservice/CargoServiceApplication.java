package com.transmarket.app.cargoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableEurekaClient
@EntityScan(basePackages = "com.transmarket.app.cargoservice.model")
public class CargoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CargoServiceApplication.class, args);
    }

}
