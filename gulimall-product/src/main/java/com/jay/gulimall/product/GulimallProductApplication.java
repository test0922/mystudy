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
 *  3、JSR303
 *  1)、給bean添加校驗註解：javax.validation.constraints，並定義自己的message提示
 *  2)、開啟校驗功能@Valid
 *  3)、給校驗的bean後再傳一個參數BindingResult，就可以取得校驗結果
 *  4)、若controller不再接收BindingResult，可使檢核異常被拋出
 *  5)、a)、不同業務中驗證方式不同，使用分組校驗，添加groups屬性指向一個接口@Null(message = "新增品牌時不能輸入id", groups = {AddGroup.class})
 *        @NotNull(message = "修改品牌時id不能為空", groups = {UpdateGroup.class})
 *        b)、@Valid 改為 @Validated({UpdateGroup.class}) 這樣更新就分組到更新驗證
 *        c)、沒有指定分組的驗證註解，在@Validated({UpdateGroup.class}) 分組的情況下不生效
 *  6)、自訂義校驗：
 *        a)、編寫自訂義校驗註解：使用@interface創建，必須有3個值message ， groups， payload
 *        b)、編寫一個自訂義的校驗器
 *        c)、關聯自訂義校驗器和自訂義校驗註解
 *  4、統一異常處理
 *  1)、@ControllerAdvice(basePackages = "com.jay.gulimall.product.controller") 指定異常處理包
 *  2)、異常處理類中宣告方法並使用@ExceptionHandler(value = Throwable.class)處理所有異常，(value = MethodArgumentNotValidException.class)則只處理驗證異常
 *  3)、以json方式處理使用@ResponseBody，@ResponseBody與@ControllerAdvice可以合併為@ResControllerAdvice
 *  4)、錯誤碼可以在common中創建枚舉類定義
 */

@SpringBootApplication
@EnableDiscoveryClient
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
