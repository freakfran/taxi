package com.fran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceMapMain8085 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMapMain8085.class,args);
    }
}
