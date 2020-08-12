package com.oe.spring.test.crontest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class CrontestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrontestApplication.class, args);
    }

}
