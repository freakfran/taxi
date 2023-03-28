package com.fran.apipassenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiPassengerApplication8081 {
    public static void main(String[] args) {
        SpringApplication.run(ApiPassengerApplication8081.class,args);
    }
}
