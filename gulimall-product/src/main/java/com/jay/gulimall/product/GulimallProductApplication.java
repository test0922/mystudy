package com.jay.gulimall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1、Mybatis-Plus
 *          1)導入依賴
 *                  <dependency>
 *                      <groupId>com.baomidou</groupId>
 *                      <artifactId>mybatis-plus-boot-starter</artifactId>
 *                      <version>3.5.11</version>
 *                  </dependency>
 *          2)配置
 *                  1.配置導入數據源
 *                      1)導入數據庫驅動
 *                      2)在application.yml配置數據源相關信息
 *                  2.配置導入MyBatis-Plus
 *                      1)使用@MapperSacn
 *                      2)告訴MyBatis-Plus, sql映射文件
 * 2、邏輯刪除
 *  1)、配置邏輯刪除的規則(可以省略)
 *  2)、被至邏輯刪除組件(高版本可以省略)
 *  3)、在bean上的屬性加上邏輯刪除註解(註解上也可以配置規則)
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
