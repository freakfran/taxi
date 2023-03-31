package com.fran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceDriverUserMain8086 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDriverUserMain8086.class);
    }
}
