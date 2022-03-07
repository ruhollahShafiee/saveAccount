package com.kadmos.service.saveAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ApiGatewayApplication.class);
        app.run(args);
    }
}
