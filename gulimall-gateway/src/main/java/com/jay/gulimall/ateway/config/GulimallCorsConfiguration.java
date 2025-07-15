package com.jay.gulimall.ateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class GulimallCorsConfiguration {
    @Bean
    public CorsWebFilter corsWebFilter(){

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        //1、配置跨域
        configuration.addAllowedHeader("*"); //請求頭
        configuration.addAllowedMethod("*");//請求方式
        configuration.setAllowedOriginPatterns(Arrays.asList(
                "http://localhost:8001"
                ));//請求來源
        configuration.setAllowCredentials(true);//是否可以攜帶cookie

        source.registerCorsConfiguration("/**", configuration);
        return new CorsWebFilter(source);
    }
}
