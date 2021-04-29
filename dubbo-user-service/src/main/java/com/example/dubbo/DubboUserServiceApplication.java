package com.example.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

// @SpringBootApplication
@EnableAutoConfiguration
public class DubboUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboUserServiceApplication.class, args);
    }

}
