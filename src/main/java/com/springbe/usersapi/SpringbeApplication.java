package com.springbe.usersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry 
@ComponentScan(basePackages = "com.springbe.usersapi")
public class SpringbeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbeApplication.class, args);
    }
}
