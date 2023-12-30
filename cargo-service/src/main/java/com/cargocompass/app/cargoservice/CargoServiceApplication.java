package com.cargocompass.app.cargoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan(basePackages = "com.cargocompass.app.cargoservice.model")
public class CargoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CargoServiceApplication.class, args);
    }

}
