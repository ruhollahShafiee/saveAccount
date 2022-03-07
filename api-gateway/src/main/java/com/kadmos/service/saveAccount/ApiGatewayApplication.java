package com.kadmos.service.saveAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ApiGatewayApplication.class);
        app.run(args);
    }
}
