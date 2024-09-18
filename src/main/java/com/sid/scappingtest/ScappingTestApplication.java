package com.sid.scappingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScappingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScappingTestApplication.class, args);
    }

}
