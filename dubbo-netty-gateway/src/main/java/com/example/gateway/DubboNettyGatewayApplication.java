package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.gateway.config")
public class DubboNettyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboNettyGatewayApplication.class, args);
    }

}
