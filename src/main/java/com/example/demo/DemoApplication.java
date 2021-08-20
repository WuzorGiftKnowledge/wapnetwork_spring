package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages="com.example.demo")
public class DemoApplication {
/**
 * 
 * @param args 
 */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }
}
