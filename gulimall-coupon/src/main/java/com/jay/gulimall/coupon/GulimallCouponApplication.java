package com.jay.gulimall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1.使用NACOS管理配置中心
 * 1).引入依賴
 *         <dependency>
 *             <groupId>com.alibaba.cloud</groupId>
 *             <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 *         </dependency>
 * 2).配置
 *    cloud:
 *     nacos:
 *       config:
 *         server-addr: 127.0.0.1:8848
 *         file-extension: properties
 *         namespace: 1bb5172c-fdac-4c7d-8977-4092b5d3f62e  ==>默認是public
 *   config:
 *     import:
 *       - nacos:gulimall-coupon
 * 3).配置中心新增當前應用名稱 (DataId)gulimall-coupon，並選擇配置時設定配置文件類型
 * 4).動態獲取配置 使用
 *      @RefreshScope
 *      @Value("${配置項的名稱}")
 *      配置中心與項目中有相同的配置優先使用配置中心的配置
 * 2.細節
 * 1).命名空間：配置隔離
 *      默認：public =>默認新增的所有配置都為public
 *      配置namespace: 時是使用空間ID不是空間名稱
 *      命名空間可以根據環境，也可以根據為服務
 *2).配置集ID：類似文件名
 *      Data Id：
 *3).配置分組：
 *      默認：DEFUALT_GROUP
 *      配置group: group名稱
 * 3.根據微服為配置 命名空間 環境配置 GROUP(DEV TEST PROD)
 */

@SpringBootApplication
@EnableDiscoveryClient
public class GulimallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallCouponApplication.class, args);
    }

}
