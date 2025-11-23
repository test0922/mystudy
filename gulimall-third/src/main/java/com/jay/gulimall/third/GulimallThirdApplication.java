package com.jay.gulimall.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GulimallThirdApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallThirdApplication.class, args);
    }

}
