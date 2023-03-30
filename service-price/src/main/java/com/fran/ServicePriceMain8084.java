package com.fran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServicePriceMain8084 {
    public static void main(String[] args) {
        SpringApplication.run(ServicePriceMain8084.class,args);
    }
}
