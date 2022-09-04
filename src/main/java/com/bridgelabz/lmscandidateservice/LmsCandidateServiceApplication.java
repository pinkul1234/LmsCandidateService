package com.bridgelabz.lmscandidateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LmsCandidateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmsCandidateServiceApplication.class, args);
    }

}
