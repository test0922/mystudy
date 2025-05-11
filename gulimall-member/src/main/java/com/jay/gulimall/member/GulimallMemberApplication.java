package com.jay.gulimall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 1.想要遠程調用其他服務
 * 1).引入open-feign
 * 2).編寫一個接個，告訴springCloud這個接口需要調用遠程服務
 *      a.聲明接口的每一個方法都是調用哪個遠程服務的哪個請求
 * 3).開啟遠程調用功能
 */

@SpringBootApplication
@EnableFeignClients(basePackages = "com.jay.gulimall.member.feign")
@EnableDiscoveryClient
public class GulimallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallMemberApplication.class, args);
    }

}
